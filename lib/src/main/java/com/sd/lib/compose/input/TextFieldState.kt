package com.sd.lib.compose.input

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

internal val LocalTextFieldState = staticCompositionLocalOf<FTextFieldState?> { null }

@Stable
interface FTextFieldState {
   val enabled: Boolean
   val isError: Boolean
   val focused: Boolean
   val text: CharSequence
   val colors: FTextFieldColors
   fun clearText()
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
internal fun FTextFieldState.textColor(): Color {
   return colors.textColor(
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
internal fun FTextFieldState.cursorColor(): Color {
   return colors.cursorColor(isError).value
}