package com.sd.lib.compose.input

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

/** 限制输入长度 */
fun TextFieldState.fSetMaxLengthFlow(maxLength: Int): Flow<CharSequence> {
  require(maxLength > 0)
  return snapshotFlow { text }.map { text ->
    if (text.length > maxLength) {
      text.take(maxLength)
    } else {
      text
    }
  }
}

/** 限制输入范围 */
fun TextFieldState.fCoerceInAndSetTextFlow(
  min: Int,
  max: Int,
  default: () -> Int = { min },
  setText: TextFieldState.(Int?) -> Unit = { value ->
    val text = value?.toString() ?: ""
    setTextAndPlaceCursorAtEnd(text)
  },
): Flow<Int?> {
  return fCoerceInFlow(min = min, max = max, default = default)
    .onEach { setText(it) }
    .distinctUntilChanged()
}

/** 限制输入范围，如果为空字符串则发射null */
fun TextFieldState.fCoerceInFlow(
  min: Int,
  max: Int,
  default: () -> Int = { min },
): Flow<Int?> {
  return snapshotFlow { text.toString() }.map { text ->
    if (text.isNotEmpty()) {
      text.toIntOrNull()?.coerceIn(min, max) ?: default()
    } else {
      null
    }
  }
}
