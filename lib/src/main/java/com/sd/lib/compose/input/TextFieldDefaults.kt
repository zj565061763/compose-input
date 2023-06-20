package com.sd.lib.compose.input

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color

object FTextFieldDefaults {
    @Composable
    fun colors(): FTextFieldColors {
        val textColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
        val disabledTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
        val errorTextColor = MaterialTheme.colorScheme.error

        val containerColor = Color.Transparent

        val cursorColor = MaterialTheme.colorScheme.primary

        val indicatorColor = MaterialTheme.colorScheme.primary
        val placeHolderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)

        return FTextFieldColors(
            focusedTextColor = textColor,
            unfocusedTextColor = textColor,
            disabledTextColor = disabledTextColor,
            errorTextColor = errorTextColor,

            focusedContainerColor = containerColor,
            unfocusedContainerColor = containerColor,
            disabledContainerColor = containerColor,
            errorContainerColor = containerColor,

            cursorColor = cursorColor,
            errorCursorColor = errorTextColor,

            selectionColors = TextSelectionColors(
                handleColor = indicatorColor,
                backgroundColor = indicatorColor.copy(alpha = 0.4f)
            ),

            focusedIndicatorColor = indicatorColor,
            unfocusedIndicatorColor = placeHolderColor,
            disabledIndicatorColor = disabledTextColor,
            errorIndicatorColor = errorTextColor,

            focusedLeadingIconColor = textColor,
            unfocusedLeadingIconColor = textColor,
            disabledLeadingIconColor = disabledTextColor,
            errorLeadingIconColor = errorTextColor,

            focusedTrailingIconColor = textColor,
            unfocusedTrailingIconColor = textColor,
            disabledTrailingIconColor = disabledTextColor,
            errorTrailingIconColor = errorTextColor,

            focusedLabelColor = indicatorColor,
            unfocusedLabelColor = placeHolderColor,
            disabledLabelColor = disabledTextColor,
            errorLabelColor = errorTextColor,

            focusedPlaceholderColor = placeHolderColor,
            unfocusedPlaceholderColor = placeHolderColor,
            disabledPlaceholderColor = placeHolderColor,
            errorPlaceholderColor = placeHolderColor,

            focusedSupportingTextColor = textColor,
            unfocusedSupportingTextColor = placeHolderColor,
            disabledSupportingTextColor = disabledTextColor,
            errorSupportingTextColor = errorTextColor,

            focusedPrefixColor = textColor,
            unfocusedPrefixColor = textColor,
            disabledPrefixColor = disabledTextColor,
            errorPrefixColor = errorTextColor,

            focusedSuffixColor = textColor,
            unfocusedSuffixColor = textColor,
            disabledSuffixColor = disabledTextColor,
            errorSuffixColor = errorTextColor,
        )
    }
}

data class FTextFieldColors internal constructor(
    val focusedTextColor: Color,
    val unfocusedTextColor: Color,
    val disabledTextColor: Color,
    val errorTextColor: Color,

    val focusedContainerColor: Color,
    val unfocusedContainerColor: Color,
    val disabledContainerColor: Color,
    val errorContainerColor: Color,

    val cursorColor: Color,
    val errorCursorColor: Color,

    val selectionColors: TextSelectionColors,

    val focusedIndicatorColor: Color,
    val unfocusedIndicatorColor: Color,
    val disabledIndicatorColor: Color,
    val errorIndicatorColor: Color,

    val focusedLeadingIconColor: Color,
    val unfocusedLeadingIconColor: Color,
    val disabledLeadingIconColor: Color,
    val errorLeadingIconColor: Color,

    val focusedTrailingIconColor: Color,
    val unfocusedTrailingIconColor: Color,
    val disabledTrailingIconColor: Color,
    val errorTrailingIconColor: Color,

    val focusedLabelColor: Color,
    val unfocusedLabelColor: Color,
    val disabledLabelColor: Color,
    val errorLabelColor: Color,

    val focusedPlaceholderColor: Color,
    val unfocusedPlaceholderColor: Color,
    val disabledPlaceholderColor: Color,
    val errorPlaceholderColor: Color,

    val focusedSupportingTextColor: Color,
    val unfocusedSupportingTextColor: Color,
    val disabledSupportingTextColor: Color,
    val errorSupportingTextColor: Color,

    val focusedPrefixColor: Color,
    val unfocusedPrefixColor: Color,
    val disabledPrefixColor: Color,
    val errorPrefixColor: Color,

    val focusedSuffixColor: Color,
    val unfocusedSuffixColor: Color,
    val disabledSuffixColor: Color,
    val errorSuffixColor: Color,
) {
    @Composable
    internal fun textColor(
        enabled: Boolean,
        isError: Boolean,
        focused: Boolean,
    ): State<Color> {
        val targetValue = when {
            !enabled -> disabledTextColor
            isError -> errorTextColor
            focused -> focusedTextColor
            else -> unfocusedTextColor
        }
        return rememberUpdatedState(targetValue)
    }

    @Composable
    internal fun containerColor(
        enabled: Boolean,
        isError: Boolean,
        focused: Boolean,
    ): State<Color> {
        val targetValue = when {
            !enabled -> disabledContainerColor
            isError -> errorContainerColor
            focused -> focusedContainerColor
            else -> unfocusedContainerColor
        }
        return animateColorAsState(targetValue, tween(durationMillis = AnimationDuration))
    }

    @Composable
    internal fun cursorColor(isError: Boolean): State<Color> {
        return rememberUpdatedState(if (isError) errorCursorColor else cursorColor)
    }

    @Composable
    internal fun indicatorColor(
        enabled: Boolean,
        isError: Boolean,
        focused: Boolean,
    ): State<Color> {
        val targetValue = when {
            !enabled -> disabledIndicatorColor
            isError -> errorIndicatorColor
            focused -> focusedIndicatorColor
            else -> unfocusedIndicatorColor
        }
        return if (enabled) {
            animateColorAsState(targetValue, tween(durationMillis = AnimationDuration))
        } else {
            rememberUpdatedState(targetValue)
        }
    }
}

@Composable
fun FTextFieldColors.toTextFieldColors(): TextFieldColors {
    return TextFieldDefaults.colors(
        focusedTextColor = focusedTextColor,
        unfocusedTextColor = unfocusedTextColor,
        disabledTextColor = disabledTextColor,
        errorTextColor = errorTextColor,

        focusedContainerColor = focusedContainerColor,
        unfocusedContainerColor = unfocusedContainerColor,
        disabledContainerColor = disabledContainerColor,
        errorContainerColor = errorContainerColor,

        cursorColor = cursorColor,
        errorCursorColor = errorCursorColor,

        selectionColors = selectionColors,

        focusedIndicatorColor = focusedIndicatorColor,
        unfocusedIndicatorColor = unfocusedIndicatorColor,
        disabledIndicatorColor = disabledIndicatorColor,
        errorIndicatorColor = errorIndicatorColor,

        focusedLeadingIconColor = focusedLeadingIconColor,
        unfocusedLeadingIconColor = unfocusedLeadingIconColor,
        disabledLeadingIconColor = disabledLeadingIconColor,
        errorLeadingIconColor = errorLeadingIconColor,

        focusedTrailingIconColor = focusedTrailingIconColor,
        unfocusedTrailingIconColor = unfocusedTrailingIconColor,
        disabledTrailingIconColor = disabledTrailingIconColor,
        errorTrailingIconColor = errorTrailingIconColor,

        focusedLabelColor = focusedLabelColor,
        unfocusedLabelColor = unfocusedLabelColor,
        disabledLabelColor = disabledLabelColor,
        errorLabelColor = errorLabelColor,

        focusedPlaceholderColor = focusedPlaceholderColor,
        unfocusedPlaceholderColor = unfocusedPlaceholderColor,
        disabledPlaceholderColor = disabledPlaceholderColor,
        errorPlaceholderColor = errorPlaceholderColor,

        focusedSupportingTextColor = focusedSupportingTextColor,
        unfocusedSupportingTextColor = unfocusedSupportingTextColor,
        disabledSupportingTextColor = disabledSupportingTextColor,
        errorSupportingTextColor = errorSupportingTextColor,

        focusedPrefixColor = focusedPrefixColor,
        unfocusedPrefixColor = unfocusedPrefixColor,
        disabledPrefixColor = disabledPrefixColor,
        errorPrefixColor = errorPrefixColor,

        focusedSuffixColor = focusedSuffixColor,
        unfocusedSuffixColor = unfocusedSuffixColor,
        disabledSuffixColor = disabledSuffixColor,
        errorSuffixColor = errorSuffixColor,
    )
}

private const val AnimationDuration = 150