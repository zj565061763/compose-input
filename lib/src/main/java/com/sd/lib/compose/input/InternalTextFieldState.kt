package com.sd.lib.compose.input

import android.annotation.SuppressLint
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.text.input.TextFieldValue

internal class InternalTextFieldState {
    private var _interactionSource: InteractionSource? by mutableStateOf(null)
    private var _focused: Boolean by mutableStateOf(false)

    var enabled: Boolean by mutableStateOf(true)
    var isError: Boolean by mutableStateOf(false)
    var colors: FTextFieldColors? by mutableStateOf(null)
    var value: TextFieldValue by mutableStateOf(TextFieldValue())
    var onValueChange: ((TextFieldValue) -> Unit)? = null

    val focusRequester = FocusRequester()

    val state: FTextFieldState = object : FTextFieldState {
        override val interactionSource: InteractionSource get() = checkNotNull(this@InternalTextFieldState._interactionSource)
        override val focused: Boolean get() = this@InternalTextFieldState._focused
        override val enabled: Boolean get() = this@InternalTextFieldState.enabled
        override val isError: Boolean get() = this@InternalTextFieldState.isError
        override val colors: FTextFieldColors get() = checkNotNull(this@InternalTextFieldState.colors)
        override val value: TextFieldValue get() = this@InternalTextFieldState.value
        override fun notifyValue(value: TextFieldValue) {
            this@InternalTextFieldState.notifyValueChange(value)
        }
    }

    @SuppressLint("ComposableNaming")
    @Composable
    fun setInteractionSource(interactionSource: InteractionSource) {
        this._interactionSource = interactionSource
        this._focused = interactionSource.collectIsFocusedAsState().value
    }

    fun notifyValueChange(value: TextFieldValue) {
        onValueChange?.invoke(value)
    }
}