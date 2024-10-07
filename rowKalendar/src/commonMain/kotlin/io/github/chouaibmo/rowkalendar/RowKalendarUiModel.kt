package io.github.chouaibmo.rowkalendar

import io.github.chouaibmo.rowkalendar.extensions.now
import kotlinx.datetime.LocalDate

/**
 * Ui model for the RowKalendar.
 *
 * @param isLoading Boolean to indicate if the calendar is loading more dates.
 * @param dates List of [LocalDate] to display in the calendar.
 * @param selectedDate The selected [LocalDate] in the calendar. By default it is the current date.
 */
internal data class RowKalendarUiModel(
    val isLoading: Boolean = false,
    val dates: List<LocalDate> = listOf(),
    val selectedDate: LocalDate = LocalDate.now(),
)