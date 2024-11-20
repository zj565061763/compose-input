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
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

suspend fun TextFieldState.fMaxLength(maxLength: Int) {
   require(maxLength > 0)
   snapshotFlow { text }.collect { text ->
      if (text.length > maxLength) {
         val take = text.take(maxLength)
         setTextAndPlaceCursorAtEnd(take.toString())
      }
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
   val showIcon = state.focused && !state.isTextEmpty
   if (!showIcon) return

   FTextFieldIcon(
      modifier = modifier,
      shape = shape,
      containerColor = containerColor,
      onClick = { state.clearText() },
   ) {
      icon()
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