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
    fun colors(
        focusedTextColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
        unfocusedTextColor: Color = focusedTextColor,
        disabledTextColor: Color = focusedTextColor.copy(0.3f),
        errorTextColor: Color = MaterialTheme.colorScheme.error,

        focusedContainerColor: Color = Color.Transparent,
        unfocusedContainerColor: Color = Color.Transparent,
        disabledContainerColor: Color = Color.Transparent,
        errorContainerColor: Color = Color.Transparent,

        cursorColor: Color = MaterialTheme.colorScheme.primary,
        errorCursorColor: Color = errorTextColor,

        selectionColors: TextSelectionColors = TextSelectionColors(
            handleColor = cursorColor,
            backgroundColor = cursorColor.copy(alpha = 0.4f)
        ),

        focusedIndicatorColor: Color = MaterialTheme.colorScheme.primary,
        unfocusedIndicatorColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
        disabledIndicatorColor: Color = disabledTextColor,
        errorIndicatorColor: Color = errorTextColor,

        focusedLeadingIconColor: Color = focusedTextColor,
        unfocusedLeadingIconColor: Color = unfocusedTextColor,
        disabledLeadingIconColor: Color = disabledTextColor,
        errorLeadingIconColor: Color = errorTextColor,

        focusedTrailingIconColor: Color = focusedTextColor,
        unfocusedTrailingIconColor: Color = unfocusedTextColor,
        disabledTrailingIconColor: Color = disabledTextColor,
        errorTrailingIconColor: Color = errorTextColor,

        focusedLabelColor: Color = focusedIndicatorColor,
        unfocusedLabelColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
        disabledLabelColor: Color = disabledTextColor,
        errorLabelColor: Color = errorTextColor,

        focusedPlaceholderColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
        unfocusedPlaceholderColor: Color = focusedPlaceholderColor,
        disabledPlaceholderColor: Color = focusedPlaceholderColor,
        errorPlaceholderColor: Color = focusedPlaceholderColor,

        focusedSupportingTextColor: Color = focusedTextColor,
        unfocusedSupportingTextColor: Color = unfocusedTextColor,
        disabledSupportingTextColor: Color = disabledTextColor,
        errorSupportingTextColor: Color = errorTextColor,

        focusedPrefixColor: Color = focusedTextColor,
        unfocusedPrefixColor: Color = unfocusedTextColor,
        disabledPrefixColor: Color = disabledTextColor,
        errorPrefixColor: Color = errorTextColor,

        focusedSuffixColor: Color = focusedTextColor,
        unfocusedSuffixColor: Color = unfocusedTextColor,
        disabledSuffixColor: Color = disabledTextColor,
        errorSuffixColor: Color = errorTextColor,
    ): FTextFieldColors {
        return FTextFieldColors(
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
    internal fun leadingIconColor(
        enabled: Boolean,
        isError: Boolean,
        focused: Boolean,
    ): State<Color> {
        return rememberUpdatedState(
            when {
                !enabled -> disabledLeadingIconColor
                isError -> errorLeadingIconColor
                focused -> focusedLeadingIconColor
                else -> unfocusedLeadingIconColor
            }
        )
    }

    @Composable
    internal fun trailingIconColor(
        enabled: Boolean,
        isError: Boolean,
        focused: Boolean
    ): State<Color> {
        return rememberUpdatedState(
            when {
                !enabled -> disabledTrailingIconColor
                isError -> errorTrailingIconColor
                focused -> focusedTrailingIconColor
                else -> unfocusedTrailingIconColor
            }
        )
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
    internal fun cursorColor(isError: Boolean): State<Color> {
        return rememberUpdatedState(if (isError) errorCursorColor else cursorColor)
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