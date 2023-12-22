package com.sd.demo.compose_input

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sd.demo.compose_input.ui.theme.AppTheme
import com.sd.lib.compose.input.FTextField
import com.sd.lib.compose.input.FTextFieldIconClear

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
        var value by remember { mutableStateOf("") }

        FTextField(
            value = value,
            maxLines = 2,
            placeholder = { Text(text = "placeholder") },
            trailingIcon = { FTextFieldIconClear() },
            onFocusRequester = { it.requestFocus() },
        ) {
            value = it
        }

        FTextField(value = "value") {}
    }
}