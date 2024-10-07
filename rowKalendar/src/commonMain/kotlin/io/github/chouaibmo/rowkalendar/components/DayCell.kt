package io.github.chouaibmo.rowkalendar.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.github.chouaibmo.rowkalendar.extensions.isBefore
import io.github.chouaibmo.rowkalendar.extensions.now
import kotlinx.datetime.LocalDate

/**
 * View that represent one day in the calendar.
 * @param modifier view modifier.
 * @param isSelected is the view selected. By default it is false.
 * @param onDateSelected callback to be called when the view is clicked. It returns the selected [LocalDate].
 */
@Composable
fun DateCell(
    modifier: Modifier = Modifier,
    date: LocalDate,
    isSelected: Boolean = false,
    onDateSelected: (LocalDate) -> Unit
) {

    val cellColor = when {
        isSelected -> MaterialTheme.colorScheme.primary
        date.isBefore(LocalDate.now()) -> Color.LightGray.copy(alpha = 0.6f)
        else -> MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
    }

    val textColor = when {
        isSelected -> MaterialTheme.colorScheme.onPrimary
        date.isBefore(LocalDate.now()) -> MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
        else -> MaterialTheme.colorScheme.onPrimary
    }

    Card(
        colors = CardDefaults.cardColors(cellColor),
        modifier = modifier
            .clickable { onDateSelected(date) }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = date.dayOfWeek.name.subSequence(0, 3).toString().uppercase(),
                color = textColor,
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = date.dayOfMonth.toString(),
                fontWeight = FontWeight.ExtraBold,
                color = textColor,
            )
        }
    }
}