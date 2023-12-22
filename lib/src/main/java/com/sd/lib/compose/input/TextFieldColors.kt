package com.sd.lib.compose.input

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color

@Immutable
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

    val textSelectionColors: TextSelectionColors,

    val focusedIndicatorColor: Color,
    val unfocusedIndicatorColor: Color,
    val disabledIndicatorColor: Color,
    val errorIndicatorColor: Color,

    val focusedPlaceholderColor: Color,
    val unfocusedPlaceholderColor: Color,
    val disabledPlaceholderColor: Color,
    val errorPlaceholderColor: Color,

    val focusedLeadingIconColor: Color,
    val unfocusedLeadingIconColor: Color,
    val disabledLeadingIconColor: Color,
    val errorLeadingIconColor: Color,

    val focusedTrailingIconColor: Color,
    val unfocusedTrailingIconColor: Color,
    val disabledTrailingIconColor: Color,
    val errorTrailingIconColor: Color,
)

@Composable
internal fun FTextFieldColors.textColor(
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
internal fun FTextFieldColors.containerColor(
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
    return animateColorAsState(targetValue, tween(durationMillis = AnimationDuration), label = "")
}

@Composable
internal fun FTextFieldColors.cursorColor(isError: Boolean): State<Color> {
    return rememberUpdatedState(if (isError) errorCursorColor else cursorColor)
}

@Composable
internal fun FTextFieldColors.indicatorColor(
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
        animateColorAsState(targetValue, tween(durationMillis = AnimationDuration), label = "")
    } else {
        rememberUpdatedState(targetValue)
    }
}

@Composable
internal fun FTextFieldColors.placeholderColor(
    enabled: Boolean,
    isError: Boolean,
    focused: Boolean,
): State<Color> {
    val targetValue = when {
        !enabled -> disabledPlaceholderColor
        isError -> errorPlaceholderColor
        focused -> focusedPlaceholderColor
        else -> unfocusedPlaceholderColor
    }
    return rememberUpdatedState(targetValue)
}

@Composable
internal fun FTextFieldColors.leadingIconColor(
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
internal fun FTextFieldColors.trailingIconColor(
    enabled: Boolean,
    isError: Boolean,
    focused: Boolean,
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

private const val AnimationDuration = 150