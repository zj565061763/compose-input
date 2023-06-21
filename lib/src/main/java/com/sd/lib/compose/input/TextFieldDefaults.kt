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
        focusedTextColor: Color? = null,
        unfocusedTextColor: Color? = null,
        disabledTextColor: Color? = null,
        errorTextColor: Color? = null,

        focusedContainerColor: Color? = null,
        unfocusedContainerColor: Color? = null,
        disabledContainerColor: Color? = null,
        errorContainerColor: Color? = null,

        cursorColor: Color? = null,
        errorCursorColor: Color? = null,

        selectionColors: TextSelectionColors? = null,

        focusedIndicatorColor: Color? = null,
        unfocusedIndicatorColor: Color? = null,
        disabledIndicatorColor: Color? = null,
        errorIndicatorColor: Color? = null,

        focusedLeadingIconColor: Color? = null,
        unfocusedLeadingIconColor: Color? = null,
        disabledLeadingIconColor: Color? = null,
        errorLeadingIconColor: Color? = null,

        focusedTrailingIconColor: Color? = null,
        unfocusedTrailingIconColor: Color? = null,
        disabledTrailingIconColor: Color? = null,
        errorTrailingIconColor: Color? = null,

        focusedLabelColor: Color? = null,
        unfocusedLabelColor: Color? = null,
        disabledLabelColor: Color? = null,
        errorLabelColor: Color? = null,

        focusedPlaceholderColor: Color? = null,
        unfocusedPlaceholderColor: Color? = null,
        disabledPlaceholderColor: Color? = null,
        errorPlaceholderColor: Color? = null,

        focusedSupportingTextColor: Color? = null,
        unfocusedSupportingTextColor: Color? = null,
        disabledSupportingTextColor: Color? = null,
        errorSupportingTextColor: Color? = null,

        focusedPrefixColor: Color? = null,
        unfocusedPrefixColor: Color? = null,
        disabledPrefixColor: Color? = null,
        errorPrefixColor: Color? = null,

        focusedSuffixColor: Color? = null,
        unfocusedSuffixColor: Color? = null,
        disabledSuffixColor: Color? = null,
        errorSuffixColor: Color? = null,
    ): FTextFieldColors {
        val tTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
        val tDisabledTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
        val tErrorTextColor = MaterialTheme.colorScheme.error

        val tContainerColor = Color.Transparent

        val tCursorColor = MaterialTheme.colorScheme.primary

        val tIndicatorColor = MaterialTheme.colorScheme.primary
        val tPlaceHolderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)

        return FTextFieldColors(
            focusedTextColor = focusedTextColor ?: tTextColor,
            unfocusedTextColor = unfocusedTextColor ?: tTextColor,
            disabledTextColor = disabledTextColor ?: tDisabledTextColor,
            errorTextColor = errorTextColor ?: tErrorTextColor,

            focusedContainerColor = focusedContainerColor ?: tContainerColor,
            unfocusedContainerColor = unfocusedContainerColor ?: tContainerColor,
            disabledContainerColor = disabledContainerColor ?: tContainerColor,
            errorContainerColor = errorContainerColor ?: tContainerColor,

            cursorColor = cursorColor ?: tCursorColor,
            errorCursorColor = errorCursorColor ?: tErrorTextColor,

            selectionColors = selectionColors ?: TextSelectionColors(
                handleColor = tIndicatorColor,
                backgroundColor = tIndicatorColor.copy(alpha = 0.4f)
            ),

            focusedIndicatorColor = focusedIndicatorColor ?: tIndicatorColor,
            unfocusedIndicatorColor = unfocusedIndicatorColor ?: tPlaceHolderColor,
            disabledIndicatorColor = disabledIndicatorColor ?: tDisabledTextColor,
            errorIndicatorColor = errorIndicatorColor ?: tErrorTextColor,

            focusedLeadingIconColor = focusedLeadingIconColor ?: tTextColor,
            unfocusedLeadingIconColor = unfocusedLeadingIconColor ?: tTextColor,
            disabledLeadingIconColor = disabledLeadingIconColor ?: tDisabledTextColor,
            errorLeadingIconColor = errorLeadingIconColor ?: tErrorTextColor,

            focusedTrailingIconColor = focusedTrailingIconColor ?: tTextColor,
            unfocusedTrailingIconColor = unfocusedTrailingIconColor ?: tTextColor,
            disabledTrailingIconColor = disabledTrailingIconColor ?: tDisabledTextColor,
            errorTrailingIconColor = errorTrailingIconColor ?: tErrorTextColor,

            focusedLabelColor = focusedLabelColor ?: tIndicatorColor,
            unfocusedLabelColor = unfocusedLabelColor ?: tPlaceHolderColor,
            disabledLabelColor = disabledLabelColor ?: tDisabledTextColor,
            errorLabelColor = errorLabelColor ?: tErrorTextColor,

            focusedPlaceholderColor = focusedPlaceholderColor ?: tPlaceHolderColor,
            unfocusedPlaceholderColor = unfocusedPlaceholderColor ?: tPlaceHolderColor,
            disabledPlaceholderColor = disabledPlaceholderColor ?: tPlaceHolderColor,
            errorPlaceholderColor = errorPlaceholderColor ?: tPlaceHolderColor,

            focusedSupportingTextColor = focusedSupportingTextColor ?: tTextColor,
            unfocusedSupportingTextColor = unfocusedSupportingTextColor ?: tPlaceHolderColor,
            disabledSupportingTextColor = disabledSupportingTextColor ?: tDisabledTextColor,
            errorSupportingTextColor = errorSupportingTextColor ?: tErrorTextColor,

            focusedPrefixColor = focusedPrefixColor ?: tTextColor,
            unfocusedPrefixColor = unfocusedPrefixColor ?: tTextColor,
            disabledPrefixColor = disabledPrefixColor ?: tDisabledTextColor,
            errorPrefixColor = errorPrefixColor ?: tErrorTextColor,

            focusedSuffixColor = focusedSuffixColor ?: tTextColor,
            unfocusedSuffixColor = unfocusedSuffixColor ?: tTextColor,
            disabledSuffixColor = disabledSuffixColor ?: tDisabledTextColor,
            errorSuffixColor = errorSuffixColor ?: tErrorTextColor,
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