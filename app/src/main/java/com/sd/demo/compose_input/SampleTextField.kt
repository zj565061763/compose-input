package com.sd.demo.compose_input

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sd.demo.compose_input.ui.theme.AppTheme
import com.sd.lib.compose.input.FTextField
import com.sd.lib.compose.input.fTextFieldState
import com.sd.lib.compose.input.indicatorColor

class SampleTextField : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Content()
            }
        }
    }
}

@Composable
private fun Content() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        SampleDefault()
        SampleCustom()
    }
}

@Composable
private fun SampleDefault(
    modifier: Modifier = Modifier,
) {
    var value by remember { mutableStateOf("") }

    FTextField(
        modifier = modifier,
        value = value,
        maxLines = 2,
        shape = RoundedCornerShape(10.dp),
        placeholder = { Text(text = "placeholder") },
        onFocusRequester = { it.requestFocus() },
    ) {
        value = it
    }
}

@Composable
private fun SampleCustom(
    modifier: Modifier = Modifier,
) {
    var value by remember { mutableStateOf("") }

    FTextField(
        modifier = modifier,
        value = value,
        minLines = 5,
        maxLines = 5,
        trailingIcon = { },
        placeholder = { Text(text = "input", fontSize = 12.sp) },
        indicator = {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .border(
                        width = 1.dp,
                        color = fTextFieldState().indicatorColor().value,
                        shape = RoundedCornerShape(10.dp),
                    )
            )
        },
    ) {
        value = it
    }
}