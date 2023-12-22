package com.sd.lib.compose.input

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun FTextField(
    value: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    minLines: Int = 1,
    maxLines: Int = minLines,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = RoundedCornerShape(0.dp),
    onFocusRequester: ((FocusRequester) -> Unit)? = null,
    colors: FTextFieldColors = FTextFieldDefaults.colors(),
    contentPadding: PaddingValues = PaddingValues(10.dp),
    indicator: (@Composable BoxScope.() -> Unit)? = {
        FTextFieldIndicatorUnderline(modifier = Modifier.align(Alignment.BottomCenter))
    },
    overlay: (@Composable BoxScope.() -> Unit)? = null,
    onValueChange: (String) -> Unit,
) {
    val onValueChangeUpdated by rememberUpdatedState(newValue = onValueChange)

    // 内部保存的值
    var fieldValue by remember {
        mutableStateOf(TextFieldValue(text = value, selection = TextRange(value.length)))
    }

    // 最终传递进去的值
    val finalFieldValue = if (fieldValue.text == value) {
        fieldValue
    } else {
        fieldValue.copy(text = value, selection = TextRange(value.length))
    }

    FTextField(
        value = finalFieldValue,
        onValueChange = {
            fieldValue = it
            onValueChangeUpdated(it.text)
        },
        modifier = modifier,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        isError = isError,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        minLines = minLines,
        maxLines = maxLines,
        interactionSource = interactionSource,
        shape = shape,
        colors = colors,
        onFocusRequester = onFocusRequester,
        contentPadding = contentPadding,
        indicator = indicator,
        overlay = overlay,
    )
}

@Composable
fun FTextField(
    value: TextFieldValue,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    minLines: Int = 1,
    maxLines: Int = minLines,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = RoundedCornerShape(0.dp),
    onFocusRequester: ((FocusRequester) -> Unit)? = null,
    colors: FTextFieldColors = FTextFieldDefaults.colors(),
    contentPadding: PaddingValues = PaddingValues(10.dp),
    indicator: (@Composable BoxScope.() -> Unit)? = {
        FTextFieldIndicatorUnderline(modifier = Modifier.align(Alignment.BottomCenter))
    },
    overlay: (@Composable BoxScope.() -> Unit)? = null,
    onValueChange: (TextFieldValue) -> Unit,
) {
    val state = remember { InternalTextFieldState() }.apply {
        this.setInteractionSource(interactionSource)
        this.enabled = enabled
        this.isError = isError
        this.colors = colors
        this.value = value
        this.onValueChange = onValueChange
    }

    if (onFocusRequester != null) {
        LaunchedEffect(onFocusRequester) {
            onFocusRequester(state.focusRequester)
        }
    }

    val textColor = textStyle.color.takeOrElse { state.state.textColor().value }
    val mergedTextStyle = textStyle.merge(TextStyle(color = textColor))

    CompositionLocalProvider(LocalTextSelectionColors provides colors.textSelectionColors) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier.focusRequester(state.focusRequester),
            enabled = enabled,
            readOnly = readOnly,
            textStyle = mergedTextStyle,
            cursorBrush = SolidColor(colors.cursorColor(isError).value),
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            interactionSource = interactionSource,
            singleLine = maxLines == 1 && minLines == 1,
            maxLines = maxLines,
            minLines = minLines,
            decorationBox = { innerTextField ->
                CompositionLocalProvider(LocalTextFieldState provides state.state) {
                    DecorationBox(
                        state = state.state,
                        shape = shape,
                        contentPadding = contentPadding,
                        innerTextField = innerTextField,
                        placeholder = placeholder,
                        leadingIcon = leadingIcon,
                        trailingIcon = trailingIcon,
                        indicator = indicator,
                        overlay = overlay,
                    )
                }
            }
        )
    }
}