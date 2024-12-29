package com.sd.lib.compose.input

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.clearText
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

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

  override fun clearText() {
    state.clearText()
  }

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