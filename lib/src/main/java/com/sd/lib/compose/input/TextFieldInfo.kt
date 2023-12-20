package com.sd.lib.compose.input

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

internal val LocalTextFieldInfo = staticCompositionLocalOf<FTextFieldInfo?> { null }

interface FTextFieldInfo {
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
fun fTextFieldInfo(): FTextFieldInfo {
    return checkNotNull(LocalTextFieldInfo.current)
}

@Composable
fun FTextFieldIndicatorUnderline(
    modifier: Modifier = Modifier,
) {
    val indicatorColor by fTextFieldInfo().indicatorColor()
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(indicatorColor)
    )
}

@Composable
fun FTextFieldIconClear(
    modifier: Modifier = Modifier,
    textFieldInfo: FTextFieldInfo = fTextFieldInfo(),
    size: Dp = 30.dp,
    padding: PaddingValues = PaddingValues(5.dp),
    icon: @Composable () -> Unit = {
        Icon(
            modifier = Modifier.size(15.dp),
            imageVector = Icons.Default.Clear,
            contentDescription = "clear",
        )
    },
) {
    val info by rememberUpdatedState(textFieldInfo)
    val showIcon = info.isFocused && info.value.text.isNotEmpty()

    FTextFieldIcon(
        modifier = modifier,
        size = size,
        padding = padding,
        onClick = if (showIcon) {
            {
                info.notifyValue("")
            }
        } else null,
    ) {
        if (showIcon) {
            icon()
        }
    }
}

@Composable
fun FTextFieldIcon(
    modifier: Modifier = Modifier,
    size: Dp = 30.dp,
    padding: PaddingValues = PaddingValues(5.dp),
    onClick: (() -> Unit)?,
    icon: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .padding(padding)
            .size(size)
            .clip(CircleShape)
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