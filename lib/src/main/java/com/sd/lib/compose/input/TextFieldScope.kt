package com.sd.lib.compose.input

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * 输入框指示器（下划线）
 */
@Composable
fun BoxScope.FTextFieldIndicatorUnderline(
   modifier: Modifier = Modifier,
   thickness: Dp = 1.dp,
) {
   val indicatorColor = fTextFieldState().indicatorColor()
   Box(
      modifier = modifier
         .fillMaxWidth()
         .height(thickness)
         .background(indicatorColor)
         .align(Alignment.BottomCenter)
   )
}

/**
 * 输入框指示器边框
 */
@Composable
fun BoxScope.FTextFieldIndicatorOutline(
   modifier: Modifier = Modifier,
   thickness: Dp = 1.dp,
   shape: Shape = RoundedCornerShape(5.dp),
) {
   val indicatorColor = fTextFieldState().indicatorColor()
   Box(
      modifier = modifier
         .matchParentSize()
         .border(
            width = thickness,
            color = indicatorColor,
            shape = shape,
         )
   )
}

/**
 * 清空输入框内容
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
 * 输入框图标容器
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