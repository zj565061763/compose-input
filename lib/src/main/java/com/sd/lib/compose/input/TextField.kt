package com.sd.lib.compose.input

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.ProvideTextStyle
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
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
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    // modify
    maxLines: Int = 1,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    // modify
    shape: Shape = RoundedCornerShape(0.dp),
    // modify add
    onFocusRequester: ((FocusRequester) -> Unit)? = null,
    // modify
    colors: FTextFieldColors = FTextFieldDefaults.colors(),
    // modify add
    contentPadding: PaddingValues = PaddingValues(vertical = 1.dp, horizontal = 5.dp),
    // modify add
    indicator: (@Composable BoxScope.() -> Unit)? = {
        FTextFieldIndicatorUnderline(modifier = Modifier.align(Alignment.BottomCenter))
    },
    // modify add
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
        label = label,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        prefix = prefix,
        suffix = suffix,
        supportingText = supportingText,
        isError = isError,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        maxLines = maxLines,
        minLines = minLines,
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
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    // modify
    maxLines: Int = 1,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    // modify
    shape: Shape = RoundedCornerShape(0.dp),
    // modify add
    onFocusRequester: ((FocusRequester) -> Unit)? = null,
    // modify
    colors: FTextFieldColors = FTextFieldDefaults.colors(),
    // modify add
    contentPadding: PaddingValues = PaddingValues(vertical = 1.dp, horizontal = 5.dp),
    // modify add
    indicator: (@Composable BoxScope.() -> Unit)? = {
        FTextFieldIndicatorUnderline(modifier = Modifier.align(Alignment.BottomCenter))
    },
    // modify add
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

    CompositionLocalProvider(LocalTextFieldState provides state.state) {
        Box(modifier = modifier) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                leadingIcon?.let { leading ->
                    Decoration(contentColor = state.state.leadingIconColor().value) {
                        leading()
                    }
                }

                InternalTextField(
                    modifier = Modifier
                        .weight(1f)
                        .focusRequester(state.focusRequester),
                    value = value,
                    onValueChange = onValueChange,
                    enabled = enabled,
                    readOnly = readOnly,
                    textStyle = textStyle,
                    label = label,
                    placeholder = placeholder,
                    leadingIcon = null,
                    trailingIcon = null,
                    prefix = prefix,
                    suffix = suffix,
                    supportingText = supportingText,
                    isError = isError,
                    visualTransformation = visualTransformation,
                    keyboardOptions = keyboardOptions,
                    keyboardActions = keyboardActions,
                    singleLine = maxLines == 1 && minLines == 1,
                    maxLines = maxLines,
                    minLines = minLines,
                    interactionSource = interactionSource,
                    shape = shape,
                    colors = colors,
                    contentPadding = contentPadding,
                )

                trailingIcon?.let { trailing ->
                    Decoration(contentColor = state.state.trailingIconColor().value) {
                        trailing()
                    }
                }
            }

            Box(modifier = Modifier.matchParentSize()) {
                indicator?.let { it() }
                overlay?.let { it() }
            }
        }
    }
}

@Composable
private fun Decoration(
    contentColor: Color,
    typography: TextStyle? = null,
    content: @Composable () -> Unit,
) {
    val contentWithColor: @Composable () -> Unit = @Composable {
        CompositionLocalProvider(
            LocalContentColor provides contentColor,
            content = content,
        )
    }
    if (typography != null) ProvideTextStyle(typography, contentWithColor) else contentWithColor()
}