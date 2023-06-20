package com.sd.lib.compose.input

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun FTextField(
    value: String,
    onValueChange: (String) -> Unit,
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
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(
        imeAction = ImeAction.Next
    ),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = RoundedCornerShape(0.dp),
    // modify
    focus: Boolean? = null,
    colors: FTextFieldColors = FTextFieldDefaults.colors(),
    contentPadding: PaddingValues = PaddingValues(horizontal = 10.dp, vertical = 2.dp),
    indicator: @Composable BoxScope.() -> Unit = {
        FTextFieldIndicatorUnderline(modifier = Modifier.align(Alignment.BottomCenter))
    },
    overlay: @Composable BoxScope.(FTextFieldInfo) -> Unit = {}
) {
    val onValueChangeUpdated by rememberUpdatedState(newValue = onValueChange)

    FTextField(
        value = TextFieldValue(text = value, selection = TextRange(value.length)),
        onValueChange = {
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
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        interactionSource = interactionSource,
        shape = shape,
        colors = colors,
        focus = focus,
        contentPadding = contentPadding,
        indicator = indicator,
        overlay = overlay,
    )
}

@Composable
fun FTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
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
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(
        imeAction = ImeAction.Next
    ),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = RoundedCornerShape(0.dp),
    // modify
    focus: Boolean? = null,
    colors: FTextFieldColors = FTextFieldDefaults.colors(),
    contentPadding: PaddingValues = PaddingValues(horizontal = 10.dp, vertical = 2.dp),
    indicator: @Composable BoxScope.() -> Unit = {
        FTextFieldIndicatorUnderline(modifier = Modifier.align(Alignment.BottomCenter))
    },
    overlay: @Composable BoxScope.(FTextFieldInfo) -> Unit = {}
) {
    val state = remember { FTextFieldState() }.apply {
        this.enabled = enabled
        this.isError = isError
        this.isFocused = interactionSource.collectIsFocusedAsState().value
        this.colors = colors
        this.value = value
        this.onValueChange = onValueChange
    }

    if (focus != null) {
        LaunchedEffect(focus) {
            if (focus) {
                delay(100)
                state.requestFocus()
            } else {
                state.freeFocus()
            }
        }
    }

    CompositionLocalProvider(
        LocalTextFieldInfo provides state.info
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 50.dp),
        ) {
            Row(
                modifier = Modifier.matchParentSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                leadingIcon?.invoke()

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
                    singleLine = singleLine,
                    maxLines = maxLines,
                    minLines = minLines,
                    interactionSource = interactionSource,
                    shape = shape,
                    colors = colors,
                    contentPadding = contentPadding,
                )

                trailingIcon?.invoke()
            }

            Box(modifier = Modifier.matchParentSize()) {
                indicator()
                overlay(state.info)
            }
        }
    }
}

private val LocalTextFieldInfo = staticCompositionLocalOf<FTextFieldInfo?> { null }

interface FTextFieldInfo {
    val enabled: Boolean

    val isError: Boolean

    val isFocused: Boolean

    val colors: FTextFieldColors

    val value: TextFieldValue

    fun notifyValue(value: String)
}

@Composable
fun fTextFieldInfo(): FTextFieldInfo {
    return checkNotNull(LocalTextFieldInfo.current)
}

@Composable
fun FTextFieldLabel(
    label: String,
    labelPrefix: String = "",
    fontSize: TextUnit = 14.sp,
    fontSizeFocused: TextUnit = 12.sp,
) {
    val textFieldInfo = fTextFieldInfo()
    val finalLabel = if (!textFieldInfo.isFocused && textFieldInfo.value.text.isNotEmpty()) {
        label
    } else {
        labelPrefix + label
    }

    val density = LocalDensity.current
    val targetValue = if (textFieldInfo.isFocused || textFieldInfo.value.text.isNotEmpty()) {
        with(density) { fontSizeFocused.toPx() }
    } else {
        with(density) { fontSize.toPx() }
    }
    val animateValue by animateFloatAsState(targetValue)

    Text(
        text = finalLabel,
        fontSize = with(density) { animateValue.toSp() }
    )
}

@Composable
fun FTextFieldIndicatorUnderline(
    modifier: Modifier = Modifier,
) {
    val indicatorColor by fTextFieldInfo().indicatorColor()
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(indicatorColor)
    )
}