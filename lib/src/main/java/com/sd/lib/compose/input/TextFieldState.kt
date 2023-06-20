package com.sd.lib.compose.input

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue

internal class FTextFieldState {
    var enabled: Boolean by mutableStateOf(false)
    var isError: Boolean by mutableStateOf(false)
    var isFocused: Boolean by mutableStateOf(false)
    var colors: FTextFieldColors? by mutableStateOf(null)
    var value: TextFieldValue by mutableStateOf(TextFieldValue())
    var onValueChange: ((TextFieldValue) -> Unit)? = null

    val focusRequester = FocusRequester()

    val info: FTextFieldInfo = object : FTextFieldInfo {
        override val enabled: Boolean get() = this@FTextFieldState.enabled
        override val isError: Boolean get() = this@FTextFieldState.isError
        override val isFocused: Boolean get() = this@FTextFieldState.isFocused
        override val colors: FTextFieldColors get() = checkNotNull(this@FTextFieldState.colors)
        override val value: TextFieldValue get() = this@FTextFieldState.value
        override fun notifyValue(value: String) {
            this@FTextFieldState.notifyValueChange(TextFieldValue(value))
        }
    }

    fun requestFocus() {
        focusRequester.requestFocus()
    }

    fun freeFocus() {
        if (isFocused) {
            focusRequester.freeFocus()
        }
    }

    fun notifyValueChange(value: TextFieldValue) {
        onValueChange?.invoke(value)
    }
}

@Composable
fun FTextFieldInfo.indicatorColor(): State<Color> {
    return colors.indicatorColor(
        enabled = enabled,
        isError = isError,
        focused = isFocused,
    )
}