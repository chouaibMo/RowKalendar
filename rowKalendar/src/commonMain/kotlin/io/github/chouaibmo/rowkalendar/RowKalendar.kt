package io.github.chouaibmo.rowkalendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.datetime.LocalDate

/**
 * Composable that displays a scrollable row calendar.
 *
 * @param modifier row modifier.
 * @param isBounded whether the calendar is bounded or not. If bounded, the calendar will not load more dates when reaching the [maxDays] limit. By default it is true.
 * @param maxDays maximum number of days to load on each side of the selected date. By default it is 365.
 * @param content composable that will be called for each date in the calendar. It takes the date, a boolean to indicate if the date is selected and a callback to be called when the date is clicked.
 */
@Composable
fun RowKalendar(
    modifier: Modifier = Modifier,
    isBounded: Boolean = true,
    maxDays: Int = 365,
    content: @Composable (date: LocalDate, isSelected: Boolean, onClick: (LocalDate) -> Unit) -> Unit
) {
    val viewModel: RowKalendarViewModel = viewModel()
    val uiState = viewModel.uiState.value
    val scrollState = rememberLazyListState(
        initialFirstVisibleItemIndex = (uiState.dates.size / 2) - 1,
        initialFirstVisibleItemScrollOffset = -10
    )

    viewModel.setMaxDaysToLoad(maxDays)
    viewModel.setIsBounded(isBounded)

    LazyRow(
        state = scrollState,
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        itemsIndexed(uiState.dates) { index, date ->

            if (index == 0 && !uiState.isLoading){
                viewModel.loadPreviousDates()
            }
            else if (index == uiState.dates.size - 1 && !uiState.isLoading) {
                viewModel.loadUpcomingDates()
            }

            val isSelected = date == uiState.selectedDate
            content(date, isSelected) {
                viewModel.selectDate(date)
            }

        }
    }
}