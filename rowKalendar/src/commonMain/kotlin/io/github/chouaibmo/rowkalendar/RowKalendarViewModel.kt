package io.github.chouaibmo.rowkalendar

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.chouaibmo.rowkalendar.extensions.now
import kotlinx.coroutines.launch
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus
import kotlinx.datetime.plus


private const val DAYS_TO_LOAD = 30
private const val MAX_DAYS_TO_LOAD = 365

internal class RowKalendarViewModel : ViewModel() {

    val uiState: MutableState<RowKalendarUiModel> = mutableStateOf(RowKalendarUiModel())

    // Manage load boundaries and limit
    private var isBounded: Boolean = true
    private var maxDaysToLoad: Int = MAX_DAYS_TO_LOAD
    private var daysLoadedPast = 0
    private var daysLoadedFuture = 0

    init {
        loadInitialDates()
    }

    /**
     * Load initial dates.
     * It loads the [maxDaysToLoad] days around the current date.
     */
    private fun loadInitialDates() {
        val today = LocalDate.now()
        val initialDates = ((-DAYS_TO_LOAD)..DAYS_TO_LOAD).map { today.plus(DatePeriod(days = it)) }

        uiState.value = uiState.value.copy(
            dates = initialDates,
            selectedDate = today,
            isLoading = false
        )

        daysLoadedPast = DAYS_TO_LOAD
        daysLoadedFuture = DAYS_TO_LOAD
    }

    /**
     * Update the UI state.
     * @param dates List of [LocalDate] to display in the calendar.
     * @param selectedDate The selected [LocalDate] in the calendar.
     * @param isLoading Boolean to indicate if the calendar is loading more dates.
     */
    private fun updateUiState(
        dates: List<LocalDate> = uiState.value.dates,
        selectedDate: LocalDate = uiState.value.selectedDate,
        isLoading: Boolean = uiState.value.isLoading
    ) {
        uiState.value = uiState.value.copy(
            dates = dates,
            selectedDate = selectedDate,
            isLoading = isLoading
        )
    }

    /**
     * Load dates.
     * @param isPast Boolean to indicate if the dates to load are in the past or in the future.
     */
    private fun loadDates(isPast: Boolean) {
        if (uiState.value.isLoading) return

        val daysLoaded = if (isPast) daysLoadedPast else daysLoadedFuture
        if (daysLoaded >= maxDaysToLoad) return

        val daysToLoad = minOf(DAYS_TO_LOAD, maxDaysToLoad - daysLoaded)
        updateUiState(isLoading = true)

        viewModelScope.launch {
            val newDates = (1..daysToLoad).map {
                if (isPast) uiState.value.dates.first().minus(DatePeriod(days = it))
                else uiState.value.dates.last().plus(DatePeriod(days = it))
            }

            val updatedDates = if (isPast) newDates.reversed() + uiState.value.dates else uiState.value.dates + newDates
            updateUiState(dates = updatedDates)

            if (isPast) daysLoadedPast += daysToLoad else daysLoadedFuture += daysToLoad
            updateUiState(isLoading = false)
        }
    }

    /**
     * Load previous dates.
     */
    fun loadPreviousDates() {
        loadDates(isPast = true)
    }

    /**
     * Load upcoming dates.
     */
    fun loadUpcomingDates() {
        loadDates(isPast = false)
    }

    /**
     * Set the maximum number of days to load on each side of the selected date to [newMaxDays].
     */
    fun setMaxDaysToLoad(newMaxDays: Int) {
        maxDaysToLoad = newMaxDays
    }

    /**
     * Set whether the calendar is bounded or not to [bounded].
     */
    fun setIsBounded(bounded: Boolean) {
        isBounded = bounded
    }

    /**
     * Set the selected date to [date].
     */
    fun selectDate(date: LocalDate) {
        updateUiState(selectedDate = date)
    }
}