package com.sd.lib.compose.input

import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue

internal val LocalTextFieldState = staticCompositionLocalOf<FTextFieldState?> { null }

interface FTextFieldState {
    val interactionSource: InteractionSource

    val focused: Boolean

    val enabled: Boolean

    val isError: Boolean

    val colors: FTextFieldColors

    val value: TextFieldValue

    fun notifyValue(value: TextFieldValue)
}

@Composable
fun fTextFieldState(): FTextFieldState {
    return checkNotNull(LocalTextFieldState.current)
}

@Composable
fun FTextFieldState.indicatorColor(): State<Color> {
    return colors.indicatorColor(
        enabled = enabled,
        isError = isError,
        focused = focused,
    )
}

@Composable
fun FTextFieldState.placeholderColor(): State<Color> {
    return colors.placeholderColor(
        enabled = enabled,
        isError = isError,
        focused = focused,
    )
}

@Composable
fun FTextFieldState.leadingIconColor(): State<Color> {
    return colors.leadingIconColor(
        enabled = enabled,
        isError = isError,
        focused = focused,
    )
}

@Composable
fun FTextFieldState.trailingIconColor(): State<Color> {
    return colors.trailingIconColor(
        enabled = enabled,
        isError = isError,
        focused = focused,
    )
}

@Composable
internal fun FTextFieldState.containerColor(): State<Color> {
    return colors.containerColor(
        enabled = enabled,
        isError = isError,
        focused = focused,
    )
}

@Composable
internal fun FTextFieldState.textColor(): State<Color> {
    return colors.textColor(
        enabled = enabled,
        isError = isError,
        focused = focused,
    )
}