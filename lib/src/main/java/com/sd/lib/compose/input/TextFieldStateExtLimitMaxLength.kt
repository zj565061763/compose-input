package com.sd.lib.compose.input

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

/** 限制输入长度 */
fun TextFieldState.fLimitMaxLengthAndSetTextFlow(
  maxLength: Int,
  setText: TextFieldState.(CharSequence) -> Unit = { setTextAndPlaceCursorAtEnd(it.toString()) },
): Flow<CharSequence> {
  return fLimitMaxLengthFlow(maxLength)
    .onEach { setText(it) }
    .distinctUntilChanged()
}

/** 限制输入长度 */
fun TextFieldState.fLimitMaxLengthFlow(maxLength: Int): Flow<CharSequence> {
  require(maxLength > 0)
  return snapshotFlow { text }.map { text ->
    if (text.length > maxLength) {
      text.take(maxLength)
    } else {
      text
    }
  }
}
