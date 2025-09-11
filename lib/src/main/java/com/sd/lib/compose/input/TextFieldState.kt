package com.sd.lib.compose.input

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Stable
interface FTextFieldState {
  val enabled: Boolean
  val isError: Boolean
  val focused: Boolean
  val text: CharSequence
  val isTextEmpty: Boolean
  val colors: FTextFieldColors
  fun clearText()
}

@Composable
fun fTextFieldState(): FTextFieldState {
  return checkNotNull(LocalTextFieldState.current)
}

internal val LocalTextFieldState = staticCompositionLocalOf<FTextFieldState?> { null }

internal fun FTextFieldState.textColor(): Color {
  return colors.textColor(
    enabled = enabled,
    isError = isError,
    focused = focused,
  )
}

internal fun FTextFieldState.containerColor(): Color {
  return colors.containerColor(
    enabled = enabled,
    isError = isError,
    focused = focused,
  )
}

internal fun FTextFieldState.indicatorColor(): Color {
  return colors.indicatorColor(
    enabled = enabled,
    isError = isError,
    focused = focused,
  )
}

internal fun FTextFieldState.placeholderColor(): Color {
  return colors.placeholderColor(
    enabled = enabled,
    isError = isError,
    focused = focused,
  )
}

internal fun FTextFieldState.leadingIconColor(): Color {
  return colors.leadingIconColor(
    enabled = enabled,
    isError = isError,
    focused = focused,
  )
}

internal fun FTextFieldState.trailingIconColor(): Color {
  return colors.trailingIconColor(
    enabled = enabled,
    isError = isError,
    focused = focused,
  )
}

internal fun FTextFieldState.cursorColor(): Color {
  return colors.cursorColor(isError)
}