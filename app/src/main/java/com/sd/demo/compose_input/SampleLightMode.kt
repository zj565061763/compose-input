package com.sd.demo.compose_input

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
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

class SampleLightMode : ComponentActivity() {
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
         .fillMaxSize()
         .padding(24.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.spacedBy(24.dp),
   ) {
      Button(onClick = onClickChangeMode) {
         Text(text = "Change Mode")
      }
      FTextField(
         modifier = Modifier.fillMaxWidth(),
         state = rememberTextFieldState(),
         placeholder = {
            Text(text = "text1")
         }
      )
      FTextField(
         modifier = Modifier.fillMaxWidth(),
         state = rememberTextFieldState(),
         placeholder = {
            Text(text = "text2")
         }
      )
   }
}