package com.sd.lib.compose.input

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicSecureTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.semantics.error
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp

@Composable
fun FSecureTextField(
  modifier: Modifier = Modifier,
  state: TextFieldState,
  enabled: Boolean = true,
  inputTransformation: InputTransformation? = null,
  textStyle: TextStyle? = null,
  keyboardOptions: KeyboardOptions = KeyboardOptions.fSecure(),
  onKeyboardAction: KeyboardActionHandler? = null,
  onTextLayout: (Density.(getResult: () -> TextLayoutResult?) -> Unit)? = null,
  interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
  textObfuscationMode: TextObfuscationMode = TextObfuscationMode.RevealLastTyped,
  textObfuscationCharacter: Char = DefaultObfuscationCharacter,

  isError: Boolean = false,
  shape: Shape = RoundedCornerShape(0.dp),
  colors: FTextFieldColors = FTextFieldDefaults.colors(),
  contentPadding: PaddingValues = PaddingValues(horizontal = 12.dp, vertical = 6.dp),

  placeholder: @Composable (() -> Unit)? = null,
  label: @Composable (() -> Unit)? = null,
  leadingIcon: @Composable (() -> Unit)? = null,
  trailingIcon: @Composable (() -> Unit)? = null,
  indicator: (@Composable BoxScope.() -> Unit)? = { FTextFieldIndicatorOutline() },
  overlay: (@Composable BoxScope.() -> Unit)? = null,
) {
  val internalState = remember(state) { TextFieldStateImpl(state) }
    .apply {
      setData(
        enabled = enabled,
        isError = isError,
        focused = interactionSource.collectIsFocusedAsState().value,
        colors = colors,
      )
    }

  val localTextStyle = LocalTextStyle.current
  val safeTextStyle = when {
    textStyle == null -> localTextStyle
    textStyle === localTextStyle -> localTextStyle
    else -> localTextStyle.merge(textStyle)
  }
  val mergedTextStyle = safeTextStyle.let { style ->
    val textColor = style.color.takeOrElse { internalState.textColor() }
    if (textColor == style.color) style else style.copy(color = textColor)
  }

  CompositionLocalProvider(LocalTextSelectionColors provides colors.textSelectionColors) {
    BasicSecureTextField(
      state = state,
      modifier = modifier
        .defaultMinSize(minWidth = 200.dp, minHeight = 56.dp)
        .let { if (isError) it.semantics { error("Input error") } else it },
      enabled = enabled,
      inputTransformation = inputTransformation,
      textStyle = mergedTextStyle,
      keyboardOptions = keyboardOptions,
      onKeyboardAction = onKeyboardAction,
      onTextLayout = onTextLayout,
      interactionSource = interactionSource,
      cursorBrush = SolidColor(internalState.cursorColor()),
      textObfuscationMode = textObfuscationMode,
      textObfuscationCharacter = textObfuscationCharacter,
      decorator = { innerTextField ->
        CompositionLocalProvider(LocalTextFieldState provides internalState) {
          DecorationBox(
            state = internalState,
            textStyle = safeTextStyle,
            shape = shape,
            contentPadding = contentPadding,
            innerTextField = innerTextField,
            placeholder = placeholder,
            label = label,
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

fun KeyboardOptions.Companion.fSecure(): KeyboardOptions {
  return SecureKeyboardOptions
}

private const val DefaultObfuscationCharacter: Char = '\u2022'

private val SecureKeyboardOptions = KeyboardOptions(
  autoCorrectEnabled = false,
  keyboardType = KeyboardType.Password
)