package com.sd.lib.compose.input

import android.annotation.SuppressLint
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue

internal class FTextFieldState {
    private var _interactionSource: InteractionSource? by mutableStateOf(null)
    private var _isFocused: Boolean by mutableStateOf(false)

    var enabled: Boolean by mutableStateOf(false)
    var isError: Boolean by mutableStateOf(false)
    var colors: FTextFieldColors? by mutableStateOf(null)
    var value: TextFieldValue by mutableStateOf(TextFieldValue())
    var onValueChange: ((TextFieldValue) -> Unit)? = null

    val focusRequester = FocusRequester()

    val textFieldInfo: FTextFieldInfo = object : FTextFieldInfo {
        override val interactionSource: InteractionSource get() = checkNotNull(this@FTextFieldState._interactionSource)
        override val isFocused: Boolean get() = this@FTextFieldState._isFocused
        override val enabled: Boolean get() = this@FTextFieldState.enabled
        override val isError: Boolean get() = this@FTextFieldState.isError
        override val colors: FTextFieldColors get() = checkNotNull(this@FTextFieldState.colors)
        override val value: TextFieldValue get() = this@FTextFieldState.value

        override fun notifyValue(value: String) {
            this@FTextFieldState.notifyValueChange(TextFieldValue(value))
        }

        override fun notifyValue(value: TextFieldValue) {
            this@FTextFieldState.notifyValueChange(value)
        }
    }

    @SuppressLint("ComposableNaming")
    @Composable
    fun setInteractionSource(interactionSource: InteractionSource) {
        this._interactionSource = interactionSource
        this._isFocused = interactionSource.collectIsFocusedAsState().value
    }

    fun requestFocus() {
        focusRequester.requestFocus()
    }

    fun freeFocus() {
        if (_isFocused) {
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
        interactionSource = interactionSource,
    )
}

@Composable
fun FTextFieldInfo.leadingIconColor(): State<Color> {
    return colors.leadingIconColor(
        enabled = enabled,
        isError = isError,
        interactionSource = interactionSource,
    )
}

@Composable
fun FTextFieldInfo.trailingIconColor(): State<Color> {
    return colors.trailingIconColor(
        enabled = enabled,
        isError = isError,
        interactionSource = interactionSource,
    )
}