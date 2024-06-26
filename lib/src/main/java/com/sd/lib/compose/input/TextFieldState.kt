package com.sd.lib.compose.input

import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue

internal val LocalTextFieldState = staticCompositionLocalOf<FTextFieldState?> { null }

@Stable
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
fun FTextFieldState.indicatorColor(): Color {
    return colors.indicatorColor(
        enabled = enabled,
        isError = isError,
        focused = focused,
    ).value
}

@Composable
fun FTextFieldState.placeholderColor(): Color {
    return colors.placeholderColor(
        enabled = enabled,
        isError = isError,
        focused = focused,
    ).value
}

@Composable
fun FTextFieldState.leadingIconColor(): Color {
    return colors.leadingIconColor(
        enabled = enabled,
        isError = isError,
        focused = focused,
    ).value
}

@Composable
fun FTextFieldState.trailingIconColor(): Color {
    return colors.trailingIconColor(
        enabled = enabled,
        isError = isError,
        focused = focused,
    ).value
}

@Composable
internal fun FTextFieldState.containerColor(): Color {
    return colors.containerColor(
        enabled = enabled,
        isError = isError,
        focused = focused,
    ).value
}

@Composable
internal fun FTextFieldState.textColor(): Color {
    return colors.textColor(
        enabled = enabled,
        isError = isError,
        focused = focused,
    ).value
}