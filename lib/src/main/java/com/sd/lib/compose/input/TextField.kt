package com.sd.lib.compose.input

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
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
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
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
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    // modify
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    // modify
    shape: Shape = RoundedCornerShape(0.dp),
    // modify add
    focus: Boolean? = null,
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
) {
    val onValueChangeUpdated by rememberUpdatedState(newValue = onValueChange)

    // 内部保存的值
    var internalFieldValue by remember {
        mutableStateOf(TextFieldValue(text = value, selection = TextRange(value.length)))
    }

    // 最终传递进去的值
    val finalFieldValue = if (internalFieldValue.text == value) {
        internalFieldValue
    } else {
        internalFieldValue.copy(text = value)
    }

    FTextField(
        value = finalFieldValue,
        onValueChange = {
            internalFieldValue = it
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
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    // modify
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    // modify
    shape: Shape = RoundedCornerShape(0.dp),
    // modify add
    focus: Boolean? = null,
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
) {
    val state = remember { FTextFieldState() }.apply {
        this.setInteractionSource(interactionSource)
        this.enabled = enabled
        this.isError = isError
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

    CompositionLocalProvider(LocalTextFieldInfo provides state.textFieldInfo) {
        Box(modifier = modifier) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                leadingIcon?.let { leading ->
                    Decoration(contentColor = state.textFieldInfo.leadingIconColor().value) {
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
                    singleLine = singleLine,
                    maxLines = maxLines,
                    minLines = minLines,
                    interactionSource = interactionSource,
                    shape = shape,
                    colors = colors,
                    contentPadding = contentPadding,
                )

                trailingIcon?.let { trailing ->
                    Decoration(contentColor = state.textFieldInfo.trailingIconColor().value) {
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

private val LocalTextFieldInfo = staticCompositionLocalOf<FTextFieldInfo?> { null }

interface FTextFieldInfo {
    val interactionSource: InteractionSource

    val isFocused: Boolean

    val enabled: Boolean

    val isError: Boolean

    val colors: FTextFieldColors

    val value: TextFieldValue

    fun notifyValue(value: String)

    fun notifyValue(value: TextFieldValue)
}

@Composable
fun fTextFieldInfo(): FTextFieldInfo {
    return checkNotNull(LocalTextFieldInfo.current)
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

@Composable
fun FTextFieldIconClear(
    modifier: Modifier = Modifier,
    textFieldInfo: FTextFieldInfo = fTextFieldInfo(),
    size: Dp = 30.dp,
    padding: PaddingValues = PaddingValues(5.dp),
    icon: @Composable () -> Unit = {
        Icon(
            modifier = Modifier.size(15.dp),
            imageVector = Icons.Default.Clear,
            contentDescription = "clear",
        )
    },
) {
    val info by rememberUpdatedState(textFieldInfo)
    val showIcon = info.isFocused && info.value.text.isNotEmpty()

    FTextFieldIcon(
        modifier = modifier,
        size = size,
        padding = padding,
        onClick = if (showIcon) {
            {
                info.notifyValue("")
            }
        } else null,
    ) {
        if (showIcon) {
            icon()
        }
    }
}

@Composable
fun FTextFieldIcon(
    modifier: Modifier = Modifier,
    size: Dp = 30.dp,
    padding: PaddingValues = PaddingValues(5.dp),
    onClick: (() -> Unit)?,
    icon: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .padding(padding)
            .size(size)
            .clip(CircleShape)
            .let {
                if (onClick != null) {
                    it.clickable { onClick() }
                } else {
                    it
                }
            },
        contentAlignment = Alignment.Center,
    ) {
        icon()
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