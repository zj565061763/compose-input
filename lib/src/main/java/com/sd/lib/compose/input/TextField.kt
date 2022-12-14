package com.sd.lib.compose.input

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
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


interface FTextFieldInfo {
    val value: String

    val fieldValue: TextFieldValue

    val isFocused: Boolean

    val colors: FTextFieldColors

    fun notifyValue(value: String)
}

data class FTextFieldColors(
    val textColor: Color,
    val cursorColor: Color,
    val containerColor: Color,
    val placeholderColor: Color,
    val unfocusedLabelColor: Color,
    val focusedLabelColor: Color,
    val unfocusedIndicatorColor: Color,
    val focusedIndicatorColor: Color,
    val textSelectionColors: TextSelectionColors,
) {
    @Composable
    internal fun textColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(if (enabled) textColor else textColor)
    }

    @Composable
    internal fun cursorColor(isError: Boolean): State<Color> {
        return rememberUpdatedState(if (isError) cursorColor else cursorColor)
    }

    internal val selectionColors: TextSelectionColors
        @Composable get() = textSelectionColors
}

object FTextFieldDefaults {
    private val _textColor: Color
        @Composable get() = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)

    private val _placeHolderColor: Color
        @Composable get() = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)

    @Composable
    fun textFieldColors(): FTextFieldColors {
        return FTextFieldColors(
            textColor = _textColor,
            cursorColor = MaterialTheme.colorScheme.primary,
            containerColor = Color.Transparent,
            placeholderColor = _placeHolderColor,
            unfocusedLabelColor = _placeHolderColor,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = _placeHolderColor,
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            textSelectionColors = TextSelectionColors(
                handleColor = MaterialTheme.colorScheme.primary,
                backgroundColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FTextField(
    value: String,
    onValueChange: (String) -> Unit,
    fieldValue: TextFieldValue? = null,
    onFieldValueChange: ((TextFieldValue) -> Unit)? = null,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    focus: Boolean? = null,
    textStyle: TextStyle = LocalTextStyle.current.copy(fontSize = 16.sp),
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(
        imeAction = ImeAction.Next
    ),
    keyboardActions: KeyboardActions = KeyboardActions(),
    singleLine: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = RoundedCornerShape(0.dp),
    colors: FTextFieldColors = FTextFieldDefaults.textFieldColors(),
    contentPadding: PaddingValues = PaddingValues(horizontal = 10.dp, vertical = 2.dp),
    indicator: @Composable BoxScope.() -> Unit = {
        FTextFieldUnderlineIndicator(
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    },
    overlay: @Composable BoxScope.(FTextFieldInfo) -> Unit = {}
) {
    val state = remember { FTextFieldState() }.apply {
        this.colors = colors
        this.onValueChange = onValueChange
        this.onFieldValueChange = onFieldValueChange
    }

    /** ?????????????????? */
    var internalFieldValue by remember {
        val initValue = TextFieldValue(text = value, selection = TextRange(value.length))
        mutableStateOf(initValue)
    }

    /** ???????????????????????? */
    val finalFieldValue = fieldValue
        ?: if (internalFieldValue.text == value) {
            internalFieldValue
        } else {
            internalFieldValue.copy(text = value)
        }
    state.fieldValue = finalFieldValue

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
                        .focusRequester(state.focusRequester)
                        .onFocusChanged { state.isFocused = it.isFocused },
                    value = finalFieldValue,
                    onValueChange = {
                        internalFieldValue = it
                        state.notifyValueChange(it)
                    },
                    enabled = enabled,
                    readOnly = readOnly,
                    textStyle = textStyle,
                    label = label,
                    placeholder = placeholder,
                    leadingIcon = null,
                    trailingIcon = null,
                    isError = isError,
                    visualTransformation = visualTransformation,
                    keyboardOptions = keyboardOptions,
                    keyboardActions = keyboardActions,
                    singleLine = singleLine,
                    maxLines = maxLines,
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
    textFieldInfo: FTextFieldInfo = fTextFieldInfo(),
) {
    val finalLabel = if (!textFieldInfo.isFocused && textFieldInfo.value.isNotEmpty()) {
        label
    } else {
        labelPrefix + label
    }

    val density = LocalDensity.current
    val targetValue = if (textFieldInfo.isFocused || textFieldInfo.value.isNotEmpty()) {
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
fun FTextFieldUnderlineIndicator(
    modifier: Modifier = Modifier,
    textFieldInfo: FTextFieldInfo = fTextFieldInfo(),
) {
    val color = if (textFieldInfo.isFocused) {
        textFieldInfo.colors.focusedLabelColor
    } else {
        textFieldInfo.colors.unfocusedIndicatorColor
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(color)
    )
}

private class FTextFieldState {
    var fieldValue: TextFieldValue by mutableStateOf(TextFieldValue())
    var isFocused: Boolean by mutableStateOf(false)
    var colors: FTextFieldColors by mutableStateOf(EmptyColors)

    var onValueChange: ((String) -> Unit)? = null
    var onFieldValueChange: ((TextFieldValue) -> Unit)? = null

    val focusRequester = FocusRequester()

    val info: FTextFieldInfo = object : FTextFieldInfo {
        override val value: String get() = this@FTextFieldState.fieldValue.text
        override val fieldValue: TextFieldValue get() = this@FTextFieldState.fieldValue
        override val isFocused: Boolean get() = this@FTextFieldState.isFocused
        override val colors: FTextFieldColors get() = this@FTextFieldState.colors
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

    fun notifyValueChange(fieldValue: TextFieldValue) {
        if (onFieldValueChange != null) {
            onFieldValueChange?.invoke(fieldValue)
        } else if (onValueChange != null) {
            onValueChange?.invoke(fieldValue.text)
        }
    }

    companion object {
        private val EmptyColors = FTextFieldColors(
            textColor = Color.Transparent,
            cursorColor = Color.Transparent,
            containerColor = Color.Transparent,
            placeholderColor = Color.Transparent,
            unfocusedLabelColor = Color.Transparent,
            focusedLabelColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            textSelectionColors = TextSelectionColors(
                handleColor = Color.Transparent,
                backgroundColor = Color.Transparent,
            ),
        )
    }
}