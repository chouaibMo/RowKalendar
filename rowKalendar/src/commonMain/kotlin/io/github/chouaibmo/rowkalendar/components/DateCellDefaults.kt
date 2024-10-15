package io.github.chouaibmo.rowkalendar.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.takeOrElse


object DateCellDefaults {

    val shape: Shape = RoundedCornerShape(8.dp)

    @Composable
    fun colors(
        selectedContainerColor: Color = MaterialTheme.colorScheme.primaryContainer,
        selectedTextColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
        pastContainerColor: Color = MaterialTheme.colorScheme.secondaryContainer,
        pastTextColor: Color = MaterialTheme.colorScheme.onSecondaryContainer,
        futureContainerColor: Color = MaterialTheme.colorScheme.secondaryContainer,
        futureTextColor: Color = MaterialTheme.colorScheme.onSecondaryContainer
    ): DateCellColors {
        return DateCellColors(
            selectedContainerColor,
            selectedTextColor,
            pastContainerColor,
            pastTextColor,
            futureContainerColor,
            futureTextColor
        )
    }

    @Composable
    fun border(
        selectedBorderColor: Color = MaterialTheme.colorScheme.primary,
        pastBorderColor: Color = Color.LightGray.copy(alpha = 0.6f),
        futureBorderColor: Color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
        selectedBorderWidth: Dp = 2.dp,
        pastBorderWidth: Dp = 1.dp,
        futureBorderWidth: Dp = 1.dp
    ): DateCellBorder {
        return DateCellBorder(
            selectedBorderColor = selectedBorderColor,
            pastBorderColor = pastBorderColor,
            futureBorderColor = futureBorderColor,
            selectedBorderWidth = selectedBorderWidth,
            pastBorderWidth = pastBorderWidth,
            futureBorderWidth = futureBorderWidth
        )
    }

    fun elevation(
        selectedElevation: Dp = 0.dp,
        pastElevation: Dp = 0.dp,
        futureElevation: Dp = 0.dp
    ): DateCellElevation {
        return DateCellElevation(
            selectedElevation = selectedElevation,
            pastElevation = pastElevation,
            futureElevation = futureElevation
        )
    }


    /**
     * Represents the colors used in the DateCell.
     * Handles the colors for the selected, past and future states of the DateCell.
     */
    class DateCellColors(
        val selectedContainerColor: Color,
        val selectedTextColor: Color,
        val pastContainerColor: Color,
        val pastTextColor: Color,
        val futureContainerColor: Color,
        val futureTextColor: Color
    ) {

        fun copy(
            selectedContainerColor: Color = this.selectedContainerColor,
            selectedTextColor: Color = this.selectedTextColor,
            pastContainerColor: Color = this.pastContainerColor,
            pastTextColor: Color = this.pastTextColor,
            futureContainerColor: Color = this.futureContainerColor,
            futureTextColor: Color = this.futureTextColor
        ): DateCellColors {
            return DateCellColors(
                selectedContainerColor = selectedContainerColor.takeOrElse { this.selectedContainerColor },
                selectedTextColor = selectedTextColor.takeOrElse { this.selectedTextColor },
                pastContainerColor = pastContainerColor.takeOrElse { this.pastContainerColor },
                pastTextColor = pastTextColor.takeOrElse { this.pastTextColor },
                futureContainerColor = futureContainerColor.takeOrElse { this.futureContainerColor },
                futureTextColor = futureTextColor.takeOrElse { this.futureTextColor }
            )
        }
    }


    /**
     * Represents the border colors and widths used in the DateCell.
     * Handles the border colors and widths for the selected, past and future states of the DateCell.
     */
    class DateCellBorder(
        val selectedBorderColor: Color,
        val pastBorderColor: Color,
        val futureBorderColor: Color,
        val selectedBorderWidth: Dp,
        val pastBorderWidth: Dp,
        val futureBorderWidth: Dp
    ) {
        fun copy(
            selectedBorderColor: Color = this.selectedBorderColor,
            selectedBorderWidth: Dp = this.selectedBorderWidth,
            pastBorderColor: Color = this.pastBorderColor,
            pastBorderWidth: Dp = this.pastBorderWidth,
            futureBorderColor: Color = this.futureBorderColor,
            futureBorderWidth: Dp = this.futureBorderWidth
        ): DateCellBorder {
            return DateCellBorder(
                selectedBorderColor = selectedBorderColor.takeOrElse { this.selectedBorderColor },
                pastBorderColor = pastBorderColor.takeOrElse { this.pastBorderColor },
                futureBorderColor = futureBorderColor.takeOrElse { this.futureBorderColor },
                selectedBorderWidth = selectedBorderWidth.takeOrElse { this.selectedBorderWidth },
                pastBorderWidth = pastBorderWidth.takeOrElse { this.pastBorderWidth },
                futureBorderWidth = futureBorderWidth.takeOrElse { this.futureBorderWidth }
            )
        }
    }

    /**
     * Represents the elevation used in the DateCell.
     * Handles the elevation for the selected, past and future states of the DateCell.
     */
    class DateCellElevation(
        val selectedElevation: Dp,
        val pastElevation: Dp,
        val futureElevation: Dp,
    ) {
        fun copy(
            selectedElevation: Dp = this.selectedElevation,
            pastElevation: Dp = this.pastElevation,
            futureElevation: Dp = this.futureElevation
        ): DateCellElevation {
            return DateCellElevation(
                selectedElevation = selectedElevation.takeOrElse { this.selectedElevation },
                pastElevation = pastElevation.takeOrElse { this.pastElevation },
                futureElevation = futureElevation.takeOrElse { this.futureElevation }
            )
        }
    }
}