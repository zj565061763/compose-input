package com.sd.demo.compose_input

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sd.demo.compose_input.ui.theme.AppTheme
import com.sd.lib.compose.input.FTextField
import com.sd.lib.compose.input.FTextFieldIndicatorOutline
import com.sd.lib.compose.input.FTextFieldIndicatorContainer

class SampleIndicator : ComponentActivity() {
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
      FTextField(
         modifier = Modifier.fillMaxWidth(),
         state = rememberTextFieldState(),
         placeholder = {
            Text(text = "indicator1")
         },
         indicator = {
            FTextFieldIndicatorOutline(
               shape = CircleShape,
               unfocusedThickness = 1.dp,
               focusedThickness = 3.dp,
            )
         },
      )
      FTextField(
         modifier = Modifier.fillMaxWidth(),
         state = rememberTextFieldState(),
         placeholder = {
            Text(text = "indicator2")
         },
         indicator = {
            FTextFieldIndicatorContainer(
               unfocusedThickness = 1.dp,
               focusedThickness = 3.dp,
            ) { color, thickness ->
               HorizontalDivider(
                  modifier = Modifier.align(Alignment.BottomCenter),
                  thickness = thickness,
                  color = color,
               )
            }
         }
      )
   }
}

@Preview
@Composable
private fun Preview() {
   Content()
}