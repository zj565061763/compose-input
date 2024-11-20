package com.sd.demo.compose_input

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sd.demo.compose_input.ui.theme.AppTheme
import com.sd.lib.compose.input.FSecureTextField

class SamplePassword : ComponentActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContent {
         AppTheme {
            Surface {
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
         .padding(24.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.spacedBy(24.dp),
   ) {
      InputPasswordView(state = rememberTextFieldState())
      InputPasswordView(state = rememberTextFieldState())
   }
}

@Composable
private fun InputPasswordView(
   modifier: Modifier = Modifier,
   state: TextFieldState,
) {
   var passwordVisible by remember { mutableStateOf(false) }

   FSecureTextField(
      modifier = modifier.fillMaxWidth(),
      state = state,
      textObfuscationMode = if (passwordVisible) TextObfuscationMode.Visible else TextObfuscationMode.RevealLastTyped,
      label = {
         Text(text = "password")
      },
      trailingIcon = {
         if (state.text.isNotEmpty()) {
            TextButton(
               onClick = { passwordVisible = !passwordVisible },
            ) {
               Text(text = if (passwordVisible) "hide" else "show")
            }
         }
      },
   )
}