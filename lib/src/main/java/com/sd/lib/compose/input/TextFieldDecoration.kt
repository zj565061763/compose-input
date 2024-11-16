package com.sd.lib.compose.input

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle

@Composable
internal fun DecorationBox(
   modifier: Modifier = Modifier,
   state: FTextFieldState,
   contentAlignment: Alignment.Vertical,
   textStyle: TextStyle,
   shape: Shape,
   contentPadding: PaddingValues,
   innerTextField: @Composable () -> Unit,
   placeholder: @Composable (BoxScope.() -> Unit)?,
   leadingIcon: @Composable (RowScope.() -> Unit)?,
   trailingIcon: @Composable (RowScope.() -> Unit)?,
   indicator: @Composable (BoxScope.() -> Unit)?,
   overlay: @Composable (BoxScope.() -> Unit)?,
) {
   Box(
      modifier = modifier.background(state.containerColor(), shape),
      contentAlignment = when (contentAlignment) {
         Alignment.Top -> Alignment.TopCenter
         Alignment.Bottom -> Alignment.BottomCenter
         else -> Alignment.Center
      },
   ) {
      Row(
         modifier = Modifier.matchParentSize(),
         verticalAlignment = Alignment.CenterVertically,
      ) {
         leadingIcon?.let {
            Decoration(state.leadingIconColor()) {
               it()
            }
         }

         ContentBox(
            modifier = Modifier.weight(1f),
            state = state,
            textStyle = textStyle,
            contentPadding = contentPadding,
            innerTextField = innerTextField,
            placeholder = placeholder,
         )

         trailingIcon?.let {
            Decoration(state.trailingIconColor()) {
               it()
            }
         }
      }

      Box(modifier = Modifier.matchParentSize()) {
         indicator?.let {
            Decoration(state.indicatorColor()) {
               it()
            }
         }
         overlay?.let { it() }
      }
   }
}

@Composable
private fun ContentBox(
   modifier: Modifier = Modifier,
   state: FTextFieldState,
   textStyle: TextStyle,
   contentPadding: PaddingValues,
   innerTextField: @Composable () -> Unit,
   placeholder: @Composable (BoxScope.() -> Unit)?,
) {
   Box(
      modifier = modifier.padding(contentPadding),
      propagateMinConstraints = true,
      contentAlignment = Alignment.Center,
   ) {
      placeholder?.let {
         Decoration(
            contentColor = state.placeholderColor(),
            textStyle = textStyle,
         ) {
            Box(
               modifier = Modifier.alpha(if (state.text.isEmpty()) 1f else 0f),
               propagateMinConstraints = true,
            ) {
               it()
            }
         }
      }
      innerTextField()
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