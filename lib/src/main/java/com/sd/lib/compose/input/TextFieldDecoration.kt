package com.sd.lib.compose.input

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import androidx.constraintlayout.compose.Dimension

@Composable
internal fun DecorationBox(
  state: FTextFieldState,
  textStyle: TextStyle,
  shape: Shape,
  contentPadding: PaddingValues,
  innerTextField: @Composable () -> Unit,
  placeholder: @Composable (() -> Unit)?,
  label: @Composable (() -> Unit)?,
  leadingIcon: @Composable (() -> Unit)?,
  trailingIcon: @Composable (() -> Unit)?,
  indicator: @Composable (BoxScope.() -> Unit)?,
  overlay: @Composable (BoxScope.() -> Unit)?,
) {
  val layoutDirection = LocalLayoutDirection.current
  val startPadding = contentPadding.calculateStartPadding(layoutDirection)
  val isTextEmpty = state.isTextEmpty

  ConstraintLayout(
    modifier = Modifier.background(state.containerColor(), shape),
    animateChangesSpec = textFieldTween(),
  ) {
    val (
      refText, refPlaceholder,
      refLeading, refTrailing,
      refIndicator, refOverlay,
    ) = createRefs()

    // Leading
    if (leadingIcon != null) {
      Box(
        modifier = Modifier.constrainAs(refLeading) {
          centerVerticallyTo(parent)
          start.linkTo(parent.start)
        }
      ) {
        Decoration(state.leadingIconColor()) {
          leadingIcon()
        }
      }
    }

    // Trailing
    if (trailingIcon != null) {
      Box(
        modifier = Modifier.constrainAs(refTrailing) {
          centerVerticallyTo(parent)
          end.linkTo(parent.end)
        }
      ) {
        Decoration(state.trailingIconColor()) {
          trailingIcon()
        }
      }
    }

    // Placeholder
    if (placeholder != null) {
      Box(
        modifier = Modifier.constrainAs(refPlaceholder) {
          centerVerticallyTo(refText)
          start.linkTo(refText.start, startPadding)
        }
      ) {
        if (isTextEmpty) {
          Decoration(
            contentColor = state.placeholderColor(),
            textStyle = textStyle,
          ) {
            placeholder()
          }
        }
      }
    }

    // Text
    Box(
      modifier = Modifier
        .constrainAs(refText) {
          centerVerticallyTo(parent)
          linkTo(
            start = if (leadingIcon != null) refLeading.end else parent.start,
            end = if (trailingIcon != null) refTrailing.start else parent.end,
          )
          width = Dimension.fillToConstraints
        }
        .padding(contentPadding)
    ) {
      innerTextField()
    }

    // Indicator
    if (indicator != null) {
      Box(
        modifier = Modifier.constrainAs(refIndicator) {
          width = Dimension.percent(1f)
          height = Dimension.percent(1f)
        }
      ) {
        Decoration(state.indicatorColor()) {
          indicator()
        }
      }
    }

    // Label
    if (label != null) {
      LabelBox(
        state = state,
        refText = refText,
        textStyle = textStyle,
        startPadding = startPadding,
        label = label,
      )
    }

    // Overlay
    if (overlay != null) {
      Box(
        modifier = Modifier.constrainAs(refOverlay) {
          width = Dimension.percent(1f)
          height = Dimension.percent(1f)
        }
      ) {
        overlay()
      }
    }
  }
}

@Composable
private fun ConstraintLayoutScope.LabelBox(
  state: FTextFieldState,
  refText: ConstrainedLayoutReference,
  textStyle: TextStyle,
  startPadding: Dp,
  label: @Composable () -> Unit,
) {
  val isFloat = state.focused || !state.isTextEmpty

  val scaleAnim by animateFloatAsState(
    targetValue = if (isFloat) 0.8f else 1f,
    animationSpec = textFieldTween(),
    label = "FTextField label scale",
  )

  val colorAnim by animateColorAsState(
    targetValue = state.labelColor(),
    animationSpec = textFieldTween(),
    label = "FTextField label color",
  )

  val progress by animateFloatAsState(
    targetValue = if (isFloat) 1f else 0f,
    animationSpec = textFieldTween(),
    label = "FTextField label progress",
  )

  val backgroundPadding = if (isFloat) 4.dp else 0.dp

  val refLabel = createRef()
  val refLabelBackground = createRef()
  val lineTop = createGuidelineFromTop(0.dp)

  // Label background
  Box(
    modifier = Modifier
      .constrainAs(refLabelBackground) {
        linkTo(
          start = refLabel.start, end = refLabel.end,
          startMargin = -backgroundPadding, endMargin = -backgroundPadding,
        )
        linkTo(
          top = refLabel.top,
          bottom = refLabel.bottom,
        )
        width = Dimension.fillToConstraints
        height = Dimension.fillToConstraints
      }
      .graphicsLayer {
        alpha = progress
        transformOrigin = TransformOrigin(0f, 0.5f)
        scaleX = scaleAnim
        scaleY = scaleAnim
      }
      .clip(RoundedCornerShape(4.dp))
      .background(MaterialTheme.colorScheme.surface)
  )

  // Label
  Box(
    modifier = Modifier
      .constrainAs(refLabel) {
        if (isFloat) {
          top.linkTo(lineTop)
          bottom.linkTo(lineTop)
          start.linkTo(refText.start, (startPadding + backgroundPadding).coerceAtLeast(16.dp))
        } else {
          centerVerticallyTo(refText)
          start.linkTo(refText.start, startPadding)
        }
      }
      .graphicsLayer {
        transformOrigin = TransformOrigin(0f, 0.5f)
        scaleX = scaleAnim
        scaleY = scaleAnim
      }
  ) {
    Decoration(
      contentColor = colorAnim,
      textStyle = textStyle,
    ) {
      label()
    }
  }
}

@Composable
private fun Decoration(
  contentColor: Color,
  content: @Composable () -> Unit,
) {
  CompositionLocalProvider(
    value = LocalContentColor provides contentColor,
    content = content,
  )
}

@Composable
private fun Decoration(
  contentColor: Color,
  textStyle: TextStyle,
  content: @Composable () -> Unit,
) {
  CompositionLocalProvider(
    LocalContentColor provides contentColor,
    LocalTextStyle provides textStyle,
    content = content,
  )
}

private fun <T> textFieldTween(): TweenSpec<T> {
  return tween(durationMillis = 200)
}