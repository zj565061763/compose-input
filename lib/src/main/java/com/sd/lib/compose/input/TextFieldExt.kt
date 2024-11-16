package com.sd.lib.compose.input

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.runtime.snapshotFlow

suspend fun TextFieldState.fMaxLength(maxLength: Int) {
   require(maxLength > 0)
   snapshotFlow { text }.collect { text ->
      if (text.length > maxLength) {
         val take = text.take(maxLength)
         setTextAndPlaceCursorAtEnd(take.toString())
      }
   }
}