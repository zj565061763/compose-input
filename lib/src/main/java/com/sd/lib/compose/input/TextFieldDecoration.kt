package com.sd.lib.compose.input

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle

@Composable
internal fun DecorationBox(
    modifier: Modifier = Modifier,
    state: FTextFieldState,
    shape: Shape,
    contentPadding: PaddingValues,
    innerTextField: @Composable () -> Unit,
    placeholder: @Composable (() -> Unit)?,
    leadingIcon: @Composable (() -> Unit)?,
    trailingIcon: @Composable (() -> Unit)?,
    indicator: (@Composable BoxScope.() -> Unit)?,
    overlay: (@Composable BoxScope.() -> Unit)?,
) {
    Box(
        modifier = modifier.background(state.containerColor().value, shape),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            leadingIcon?.let {
                Decoration(contentColor = state.leadingIconColor().value) {
                    it()
                }
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(contentPadding),
                propagateMinConstraints = true,
                contentAlignment = Alignment.Center,
            ) {
                placeholder?.let {
                    if (state.value.text.isEmpty()) {
                        Row(
                            modifier = Modifier.matchParentSize(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Decoration(contentColor = state.placeholderColor().value) {
                                it()
                            }
                        }
                    }
                }
                innerTextField()
            }

            trailingIcon?.let {
                Decoration(contentColor = state.trailingIconColor().value) {
                    it()
                }
            }
        }

        Box(modifier = Modifier.matchParentSize()) {
            indicator?.let {
                Decoration(contentColor = state.indicatorColor().value) {
                    it()
                }
            }
            overlay?.let { it() }
        }
    }
}

@Composable
private fun Decoration(
    contentColor: Color,
    typography: TextStyle? = null,
    content: @Composable () -> Unit,
) {
    val contentWithColor: @Composable () -> Unit = @Composable {
        CompositionLocalProvider(
            LocalContentColor provides contentColor,
            content = content,
        )
    }
    if (typography != null) ProvideTextStyle(typography, contentWithColor) else contentWithColor()
}