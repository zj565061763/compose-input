package com.sd.lib.compose.input

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object FTextFieldDefaults {
   @Suppress("NAME_SHADOWING")
   @Composable
   fun colors(
      // Text
      focusedTextColor: Color? = null,
      unfocusedTextColor: Color? = null,
      disabledTextColor: Color? = null,
      errorTextColor: Color? = null,

      // Container
      focusedContainerColor: Color? = null,
      unfocusedContainerColor: Color? = null,
      disabledContainerColor: Color? = null,
      errorContainerColor: Color? = null,

      // Indicator
      focusedIndicatorColor: Color? = null,
      unfocusedIndicatorColor: Color? = null,
      disabledIndicatorColor: Color? = null,
      errorIndicatorColor: Color? = null,

      // Placeholder
      focusedPlaceholderColor: Color? = null,
      unfocusedPlaceholderColor: Color? = null,
      disabledPlaceholderColor: Color? = null,
      errorPlaceholderColor: Color? = null,

      // Leading
      focusedLeadingIconColor: Color? = null,
      unfocusedLeadingIconColor: Color? = null,
      disabledLeadingIconColor: Color? = null,
      errorLeadingIconColor: Color? = null,

      // Trailing
      focusedTrailingIconColor: Color? = null,
      unfocusedTrailingIconColor: Color? = null,
      disabledTrailingIconColor: Color? = null,
      errorTrailingIconColor: Color? = null,

      // Cursor
      cursorColor: Color? = null,
      errorCursorColor: Color? = null,
   ): FTextFieldColors {
      val focusedTextColor = focusedTextColor ?: MaterialTheme.colorScheme.onSurface
      val unfocusedTextColor = unfocusedTextColor ?: focusedTextColor
      val disabledTextColor = disabledTextColor ?: focusedTextColor.copy(alpha = 0.3f)
      val errorTextColor = errorTextColor ?: MaterialTheme.colorScheme.error

      val focusedPlaceholderColor = focusedPlaceholderColor ?: MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)

      val focusedIndicatorColor = focusedIndicatorColor ?: MaterialTheme.colorScheme.primary
      val unfocusedIndicatorColor = unfocusedIndicatorColor ?: MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)

      val cursorColor = cursorColor ?: focusedIndicatorColor
      return FTextFieldColors(
         // Text
         focusedTextColor = focusedTextColor,
         unfocusedTextColor = unfocusedTextColor,
         disabledTextColor = disabledTextColor,
         errorTextColor = errorTextColor,

         // Container
         focusedContainerColor = focusedContainerColor ?: Color.Transparent,
         unfocusedContainerColor = unfocusedContainerColor ?: Color.Transparent,
         disabledContainerColor = disabledContainerColor ?: Color.Transparent,
         errorContainerColor = errorContainerColor ?: Color.Transparent,

         // Indicator
         focusedIndicatorColor = focusedIndicatorColor,
         unfocusedIndicatorColor = unfocusedIndicatorColor,
         disabledIndicatorColor = disabledIndicatorColor ?: disabledTextColor,
         errorIndicatorColor = errorIndicatorColor ?: errorTextColor,

         // Placeholder
         focusedPlaceholderColor = focusedPlaceholderColor,
         unfocusedPlaceholderColor = unfocusedPlaceholderColor ?: focusedPlaceholderColor,
         disabledPlaceholderColor = disabledPlaceholderColor ?: focusedPlaceholderColor,
         errorPlaceholderColor = errorPlaceholderColor ?: focusedPlaceholderColor,

         // Leading
         focusedLeadingIconColor = focusedLeadingIconColor ?: focusedTextColor,
         unfocusedLeadingIconColor = unfocusedLeadingIconColor ?: unfocusedTextColor,
         disabledLeadingIconColor = disabledLeadingIconColor ?: disabledTextColor,
         errorLeadingIconColor = errorLeadingIconColor ?: errorTextColor,

         // Trailing
         focusedTrailingIconColor = focusedTrailingIconColor ?: focusedTextColor,
         unfocusedTrailingIconColor = unfocusedTrailingIconColor ?: unfocusedTextColor,
         disabledTrailingIconColor = disabledTrailingIconColor ?: disabledTextColor,
         errorTrailingIconColor = errorTrailingIconColor ?: errorTextColor,

         // Cursor
         cursorColor = cursorColor,
         errorCursorColor = errorCursorColor ?: errorTextColor,

         textSelectionColors = TextSelectionColors(
            handleColor = cursorColor,
            backgroundColor = cursorColor.copy(alpha = 0.4f)
         )
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