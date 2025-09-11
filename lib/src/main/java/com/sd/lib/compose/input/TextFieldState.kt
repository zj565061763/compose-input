package com.sd.lib.compose.input

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.clearText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Composable
fun fTextFieldState(): FTextFieldState {
  return checkNotNull(LocalTextFieldState.current)
}

internal val LocalTextFieldState = staticCompositionLocalOf<FTextFieldState?> { null }

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

internal class TextFieldStateImpl(
  private val state: TextFieldState,
) : FTextFieldState {
  private var _enabled by mutableStateOf(true)
  private var _isError by mutableStateOf(false)
  private var _focused by mutableStateOf(false)
  private var _colors by mutableStateOf<FTextFieldColors?>(null)

  override val enabled: Boolean get() = _enabled
  override val isError: Boolean get() = _isError
  override val focused: Boolean get() = _focused
  override val colors: FTextFieldColors get() = checkNotNull(_colors)
  override val text: CharSequence get() = state.text
  override val isTextEmpty: Boolean by derivedStateOf { state.text.isEmpty() }
  override fun clearText() = state.clearText()

  fun setData(
    enabled: Boolean,
    isError: Boolean,
    focused: Boolean,
    colors: FTextFieldColors,
  ) {
    _enabled = enabled
    _isError = isError
    _focused = focused
    _colors = colors
  }
}

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