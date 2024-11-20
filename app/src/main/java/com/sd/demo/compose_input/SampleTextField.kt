package com.sd.demo.compose_input

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sd.demo.compose_input.ui.theme.AppTheme
import com.sd.lib.compose.input.FSecureTextField
import com.sd.lib.compose.input.FTextField
import com.sd.lib.compose.input.FTextFieldIconClear
import com.sd.lib.compose.input.fMaxLength

class SampleTextField : ComponentActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContent {
         var isLight by remember { mutableStateOf(true) }
         AppTheme(isLight = isLight) {
            Surface {
               Content(
                  onClickChangeMode = {
                     isLight = !isLight
                  }
               )
            }
         }
      }
   }
}

@Composable
private fun Content(
   onClickChangeMode: () -> Unit,
) {
   Column(
      modifier = Modifier
         .verticalScroll(rememberScrollState())
         .fillMaxSize()
         .padding(10.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.spacedBy(24.dp),
   ) {
      Button(onClick = onClickChangeMode) {
         Text(text = "Change Mode")
      }
      Sample()
      SampleLabel()
      SampleSecure()
      SampleMaxLength()
      SampleError()
   }
}

@Composable
private fun Sample(
   modifier: Modifier = Modifier,
) {
   val state = rememberTextFieldState()

   FTextField(
      modifier = modifier.fillMaxWidth(),
      state = state,
      contentPadding = PaddingValues(
         horizontal = 16.dp,
         vertical = 8.dp,
      ),
      placeholder = {
         Text(
            text = "Enter your email",
            modifier = Modifier.background(Color.Red.copy(0.2f))
         )
      },
      trailingIcon = {
         FTextFieldIconClear()
      }
   )
}

@Composable
private fun SampleLabel(
   modifier: Modifier = Modifier,
) {
   val state = rememberTextFieldState()

   FTextField(
      modifier = modifier.fillMaxWidth(),
      state = state,
      label = {
         Text(text = "Enter your username")
      },
   )
}

@Composable
private fun SampleSecure(
   modifier: Modifier = Modifier,
) {
   val state = rememberTextFieldState()

   FSecureTextField(
      modifier = modifier.fillMaxWidth(),
      state = state,
      placeholder = {
         Text(text = "Enter you password")
      }
   )
}

@Composable
private fun SampleMaxLength(
   modifier: Modifier = Modifier,
) {
   val state = rememberTextFieldState()
   val maxLength = 10

   FTextField(
      modifier = modifier.fillMaxWidth(),
      state = state,
      placeholder = {
         Text(text = "max input length $maxLength")
      }
   )

   LaunchedEffect(state, maxLength) {
      state.fMaxLength(maxLength)
   }
}

@Composable
private fun SampleError(
   modifier: Modifier = Modifier,
) {
   val state = rememberTextFieldState()

   FTextField(
      modifier = modifier.fillMaxWidth(),
      state = state,
      isError = true,
      placeholder = {
         Text(text = "input")
      }
   )
}

@Preview
@Composable
private fun Preview() {
   Content(
      onClickChangeMode = {},
   )
}