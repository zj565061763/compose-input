package com.sd.lib.compose.input

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.semantics.error
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun FTextField(
   modifier: Modifier = Modifier,
   state: TextFieldState,
   enabled: Boolean = true,
   readOnly: Boolean = false,
   inputTransformation: InputTransformation? = null,
   textStyle: TextStyle? = null,
   keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
   onKeyboardAction: KeyboardActionHandler? = null,
   onTextLayout: (Density.(getResult: () -> TextLayoutResult?) -> Unit)? = null,
   interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
   outputTransformation: OutputTransformation? = null,

   minLines: Int = 1,
   maxLines: Int = 1,
   isError: Boolean = false,
   shape: Shape = RoundedCornerShape(0.dp),
   colors: FTextFieldColors = FTextFieldDefaults.colors(),
   contentPadding: PaddingValues = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
   contentAlignment: Alignment.Vertical = Alignment.CenterVertically,

   indicator: (@Composable BoxScope.() -> Unit)? = { FTextFieldIndicator() },
   placeholder: @Composable (BoxScope.() -> Unit)? = null,
   leadingIcon: @Composable (RowScope.() -> Unit)? = null,
   trailingIcon: @Composable (RowScope.() -> Unit)? = null,
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

   val lineLimits = when {
      minLines == 1 && maxLines == 1 -> TextFieldLineLimits.SingleLine
      minLines == 1 && maxLines == Int.MAX_VALUE -> TextFieldLineLimits.Default
      else -> TextFieldLineLimits.MultiLine(minLines, maxLines)
   }

   CompositionLocalProvider(LocalTextSelectionColors provides colors.textSelectionColors) {
      BasicTextField(
         state = state,
         modifier = modifier.let {
            if (isError) it.semantics { error("Input error") } else it
         },
         enabled = enabled,
         readOnly = readOnly,
         inputTransformation = inputTransformation,
         textStyle = mergedTextStyle,
         keyboardOptions = keyboardOptions,
         onKeyboardAction = onKeyboardAction,
         lineLimits = lineLimits,
         onTextLayout = onTextLayout,
         interactionSource = interactionSource,
         cursorBrush = SolidColor(internalState.cursorColor()),
         outputTransformation = outputTransformation,
         decorator = { innerTextField ->
            CompositionLocalProvider(LocalTextFieldState provides internalState) {
               DecorationBox(
                  state = internalState,
                  contentAlignment = contentAlignment,
                  textStyle = safeTextStyle,
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

//-------------------- Ext --------------------

/**
 * 指示器边框
 */
@Composable
fun BoxScope.FTextFieldIndicator(
   modifier: Modifier = Modifier,
   shape: Shape = MaterialTheme.shapes.extraSmall,
   unfocusedThickness: Dp = 1.dp,
   focusedThickness: Dp = unfocusedThickness * 1.2f,
) {
   val state = fTextFieldState()
   val thicknessAnim = animateDpAsState(
      targetValue = if (state.focused) focusedThickness else unfocusedThickness,
      label = "TextField indicator thickness"
   )
   Box(
      modifier = modifier
         .matchParentSize()
         .border(
            width = thicknessAnim.value,
            color = state.indicatorColor(),
            shape = shape,
         )
   )
}

/**
 * 清空内容
 */
@Composable
fun FTextFieldIconClear(
   modifier: Modifier = Modifier,
   shape: Shape = CircleShape,
   containerColor: Color = Color.Transparent,
   icon: @Composable () -> Unit = {
      Icon(
         modifier = Modifier.size(16.dp),
         imageVector = Icons.Default.Clear,
         contentDescription = "clear",
      )
   },
) {
   val state = fTextFieldState()
   val showIcon = state.focused && state.text.isNotEmpty()

   FTextFieldIcon(
      modifier = modifier,
      shape = shape,
      containerColor = containerColor,
      onClick = if (showIcon) {
         {
            state.clearText()
         }
      } else null,
   ) {
      if (showIcon) {
         icon()
      }
   }
}

/**
 * 图标容器
 */
@Composable
fun FTextFieldIcon(
   modifier: Modifier = Modifier,
   shape: Shape = CircleShape,
   containerColor: Color = Color.Transparent,
   onClick: (() -> Unit)? = null,
   icon: @Composable () -> Unit,
) {
   Box(
      modifier = modifier
         .defaultMinSize(24.dp, 24.dp)
         .background(color = containerColor, shape = shape)
         .clip(shape)
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