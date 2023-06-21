package com.sd.demo.compose_input

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        Sample(
            modifier = Modifier.background(MaterialTheme.colorScheme.surfaceVariant),
            labelText = "label",
        )
        Sample(labelText = "用户名")
        Sample(labelText = "密码")
    }
}

@Composable
private fun SampleDefault(
    modifier: Modifier = Modifier,
) {
    var value by remember { mutableStateOf("") }

    TextField(
        value = value,
        onValueChange = { value = it },
        modifier = modifier.fillMaxWidth(),
        label = { Text(text = "label") },
        leadingIcon = {
            IconButton(
                onClick = {},
                modifier = modifier
            ) {
                Icon(
                    modifier = Modifier.width(20.dp),
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "Search",
                )
            }
        },
        trailingIcon = {
            IconButton(
                onClick = {},
                modifier = modifier
            ) {
                Icon(
                    modifier = Modifier.size(15.dp),
                    imageVector = Icons.Default.Clear,
                    contentDescription = "Clear"
                )
            }
        },
    )
}

@Composable
private fun Sample(
    labelText: String,
    modifier: Modifier = Modifier,
) {
    var value by remember { mutableStateOf("") }

    FTextField(
        modifier = modifier,
        value = value,
        onValueChange = { value = it },
        label = {
            Text(text = labelText)
        },
        trailingIcon = {
            FTextFieldIconClear()
        },
    )
}