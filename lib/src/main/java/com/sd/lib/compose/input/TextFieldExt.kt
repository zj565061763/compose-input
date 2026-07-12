package com.sd.lib.compose.input

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

/** 限制输入长度 */
suspend fun TextFieldState.fSetMaxLength(maxLength: Int) {
  require(maxLength > 0)
  snapshotFlow { text }.collect { text ->
    if (text.length > maxLength) {
      val take = text.take(maxLength)
      setTextAndPlaceCursorAtEnd(take.toString())
    }
  }
}

/** 限制输入范围 */
fun TextFieldState.fCoerceIn(min: Int, max: Int): Flow<Int?> {
  return snapshotFlow { text.toString() }.map { text ->
    if (text.isEmpty()) {
      null
    } else {
      text.toIntOrNull()?.coerceIn(min, max) ?: min
    }
  }.distinctUntilChanged()
}

//-------------------- Ext --------------------

/** 指示器边框 */
@Composable
fun BoxScope.FTextFieldIndicatorOutline(
  modifier: Modifier = Modifier,
  color: Color = Color.Unspecified,
  shape: Shape = MaterialTheme.shapes.extraSmall,
  unfocusedThickness: Dp = 1.dp,
  focusedThickness: Dp = unfocusedThickness * 1.2f,
) {
  FTextFieldIndicatorContainer(
    color = color,
    unfocusedThickness = unfocusedThickness,
    focusedThickness = focusedThickness,
  ) { c, t ->
    Box(
      modifier = modifier
        .matchParentSize()
        .border(width = t, color = c, shape = shape)
    )
  }
}

/** 指示器边框 */
@Composable
fun BoxScope.FTextFieldIndicatorUnderline(
  modifier: Modifier = Modifier,
  color: Color = Color.Unspecified,
  unfocusedThickness: Dp = 1.dp,
  focusedThickness: Dp = unfocusedThickness * 1.2f,
) {
  FTextFieldIndicatorContainer(
    color = color,
    unfocusedThickness = unfocusedThickness,
    focusedThickness = focusedThickness,
  ) { c, t ->
    HorizontalDivider(
      modifier = modifier.align(Alignment.BottomCenter),
      thickness = t,
      color = c,
    )
  }
}

/** 指示器容器 */
@Composable
fun FTextFieldIndicatorContainer(
  color: Color = Color.Unspecified,
  unfocusedThickness: Dp = 1.dp,
  focusedThickness: Dp = unfocusedThickness * 1.2f,
  content: @Composable (color: Color, thickness: Dp) -> Unit,
) {
  val state = fTextFieldState()
  val thicknessAnim by animateDpAsState(
    targetValue = if (state.focused) focusedThickness else unfocusedThickness,
    label = "TextField indicator thickness",
  )
  content(
    color.takeOrElse { state.indicatorColor() },
    thicknessAnim,
  )
}

/** 清空内容图标 */
@Composable
fun FTextFieldIconClear(
  modifier: Modifier = Modifier,
  shape: Shape = CircleShape,
  containerColor: Color = Color.Transparent,
  icon: @Composable () -> Unit = { Text(text = "x", fontSize = 16.sp) },
) {
  val state = fTextFieldState()
  val showIcon = state.focused && !state.isTextEmpty
  if (showIcon) {
    FTextFieldIconContainer(
      modifier = modifier,
      shape = shape,
      containerColor = containerColor,
      onClick = { state.clearText() },
    ) {
      icon()
    }
  } else {
    Box(modifier = modifier)
  }
}

/** 图标容器 */
@Composable
fun FTextFieldIconContainer(
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
          it.clickable(interactionSource = null, indication = null, onClick = onClick)
        } else {
          it
        }
      },
    contentAlignment = Alignment.Center,
  ) {
    icon()
  }
}