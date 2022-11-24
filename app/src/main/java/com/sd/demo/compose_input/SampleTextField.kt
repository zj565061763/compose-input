package com.sd.demo.compose_input

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sd.demo.compose_input.ui.theme.AppTheme
import com.sd.lib.compose.input.FTextField
import com.sd.lib.compose.input.FTextFieldLabel
import com.sd.lib.compose.input.LocalFTextFieldInfo

class SampleTextField : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Content()
                }
            }
        }
    }
}

@Composable
private fun Content() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        SampleNormal(
            modifier = Modifier
                .height(40.dp)
                .background(MaterialTheme.colorScheme.surfaceVariant)
        )
        SampleClearText(labelText = "用户名")
        SampleClearText(labelText = "密码")
    }
}

@Composable
private fun SampleNormal(
    modifier: Modifier = Modifier,
) {
    var value by remember { mutableStateOf("") }

    FTextField(
        value = value,
        onValueChange = { value = it },
        modifier = modifier.fillMaxWidth(),
    )
}

@Composable
private fun SampleClearText(
    labelText: String,
    modifier: Modifier = Modifier,
) {
    var value by remember { mutableStateOf("") }

    FTextField(
        value = value,
        onValueChange = { value = it },
        label = {
            FTextFieldLabel(labelText)
        },
        trailingIcon = {
            val info = checkNotNull(LocalFTextFieldInfo.current)
            if (info.isFocused && info.value.isNotEmpty()) {
                IconButton(
                    onClick = { value = "" },
                    modifier = Modifier.size(30.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "clear",
                        modifier = Modifier.size(15.dp)
                    )
                }
            }
        },
        modifier = modifier,
    )
}