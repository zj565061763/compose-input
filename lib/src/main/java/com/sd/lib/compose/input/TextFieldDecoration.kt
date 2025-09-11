package com.sd.lib.compose.input

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle

@Composable
internal fun DecorationBox(
  state: FTextFieldState,
  textStyle: TextStyle,
  shape: Shape,
  contentPadding: PaddingValues,
  innerTextField: @Composable () -> Unit,
  placeholder: @Composable (() -> Unit)?,
  leadingIcon: @Composable (() -> Unit)?,
  trailingIcon: @Composable (() -> Unit)?,
  indicator: @Composable (BoxScope.() -> Unit)?,
  overlay: @Composable (BoxScope.() -> Unit)?,
) {
  Box(modifier = Modifier.background(state.containerColor(), shape)) {
    Row(
      modifier = Modifier.matchParentSize(),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      // Leading
      if (leadingIcon != null) {
        Decoration(state.leadingIconColor()) {
          leadingIcon()
        }
      }

      Box(
        modifier = Modifier
          .weight(1f)
          .padding(contentPadding),
        contentAlignment = Alignment.CenterStart,
        propagateMinConstraints = true,
      ) {
        // Text
        innerTextField()

        // Placeholder
        if (placeholder != null && state.isTextEmpty) {
          Decoration(
            contentColor = state.placeholderColor(),
            textStyle = textStyle,
          ) {
            placeholder()
          }
        }
      }

      // Trailing
      if (trailingIcon != null) {
        Decoration(state.trailingIconColor()) {
          trailingIcon()
        }
      }
    }

    // Indicator
    if (indicator != null) {
      Decoration(state.indicatorColor()) {
        indicator()
      }
    }

    // Overlay
    if (overlay != null) {
      overlay()
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