package com.sd.lib.compose.input

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

internal val LocalTextFieldState = staticCompositionLocalOf<FTextFieldState?> { null }

interface FTextFieldState {
    val interactionSource: InteractionSource

    val isFocused: Boolean

    val enabled: Boolean

    val isError: Boolean

    val colors: FTextFieldColors

    val value: TextFieldValue

    fun notifyValue(value: String)

    fun notifyValue(value: TextFieldValue)
}

@Composable
fun fTextFieldState(): FTextFieldState {
    return checkNotNull(LocalTextFieldState.current)
}

/**
 * 输入框指示器（下划线）
 */
@Composable
fun FTextFieldIndicatorUnderline(
    modifier: Modifier = Modifier,
) {
    val indicatorColor by fTextFieldState().indicatorColor()
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(indicatorColor)
    )
}

/**
 * 清空输入框内容
 */
@Composable
fun FTextFieldIconClear(
    modifier: Modifier = Modifier,
    shape: Shape = CircleShape,
    backgroundColor: Color = Color.Transparent,
    size: Dp = 30.dp,
    icon: @Composable () -> Unit = {
        Icon(
            modifier = Modifier.size(15.dp),
            imageVector = Icons.Default.Clear,
            contentDescription = "clear",
        )
    },
) {
    val state = fTextFieldState()
    val showIcon = state.isFocused && state.value.text.isNotEmpty()

    FTextFieldIcon(
        modifier = modifier,
        shape = shape,
        backgroundColor = backgroundColor,
        size = size,
        onClick = if (showIcon) {
            {
                state.notifyValue("")
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
    backgroundColor: Color = Color.Transparent,
    size: Dp = 30.dp,
    onClick: (() -> Unit)?,
    icon: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .background(color = backgroundColor, shape = shape)
            .size(size)
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