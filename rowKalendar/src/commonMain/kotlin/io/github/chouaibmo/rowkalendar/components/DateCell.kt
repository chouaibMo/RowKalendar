package io.github.chouaibmo.rowkalendar.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.github.chouaibmo.rowkalendar.extensions.isBefore
import io.github.chouaibmo.rowkalendar.extensions.now
import kotlinx.datetime.LocalDate

/**
 * View that represents one day in the calendar.
 * @param modifier view modifier.
 * @param date the date to be displayed in the cell.
 * @param isSelected whether the view is selected. Default is false.
 * @param onDateSelected callback triggered when the cell is clicked, returning the selected [LocalDate].
 * @param shape defines the shape of this card's container, border, and shadow.
 * @param colors DateCellColors to resolve the colors used in different states of the card.
 * @param elevation DateCellElevation to resolve the shadow size and primary color overlay.
 * @param border optional DateCellBorder to draw around the container of the card.
 */
@Composable
fun DateCell(
    modifier: Modifier = Modifier,
    date: LocalDate,
    isSelected: Boolean = false,
    onDateSelected: (LocalDate) -> Unit,
    shape: Shape = DateCellDefaults.shape,
    colors: DateCellDefaults.DateCellColors = DateCellDefaults.colors(),
    elevation: DateCellDefaults.DateCellElevation = DateCellDefaults.elevation(),
    border: DateCellDefaults.DateCellBorder? = null
) {

    val cellColor = when {
        isSelected -> colors.selectedContainerColor
        date.isBefore(LocalDate.now()) -> colors.pastContainerColor
        else -> colors.futureContainerColor
    }

    val textColor = when {
        isSelected -> colors.selectedTextColor
        date.isBefore(LocalDate.now()) -> colors.pastTextColor
        else -> colors.futureTextColor
    }

    val cellBorder = border?.let {
        when {
            isSelected -> BorderStroke(it.selectedBorderWidth, it.selectedBorderColor)
            date.isBefore(LocalDate.now()) -> BorderStroke(it.pastBorderWidth, it.pastBorderColor)
            else -> BorderStroke(it.futureBorderWidth, it.futureBorderColor)
        }
    }

    Card(
        modifier = modifier.clickable { onDateSelected(date) },
        shape = shape,
        border = cellBorder,
        colors = CardDefaults.cardColors(containerColor = cellColor),
        elevation = CardDefaults.cardElevation(defaultElevation = elevation.defaultElevation)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = date.dayOfWeek.name.subSequence(0, 3).toString().uppercase(),
                color = textColor
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = date.dayOfMonth.toString(),
                fontWeight = FontWeight.ExtraBold,
                color = textColor
            )
        }
    }
}