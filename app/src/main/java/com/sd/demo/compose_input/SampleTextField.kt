package com.sd.demo.compose_input

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.sd.demo.compose_input.ui.theme.AppTheme
import com.sd.lib.compose.input.FTextField
import com.sd.lib.compose.input.FTextFieldIconClear
import com.sd.lib.compose.input.FTextFieldIndicatorOutline
import com.sd.lib.compose.input.fMaxLength

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
      Sample()
      SampleMaxLength()
   }
}

@Composable
private fun Sample(
   modifier: Modifier = Modifier,
) {
   val state = rememberTextFieldState()

   FTextField(
      modifier = modifier,
      state = state,
      contentPadding = PaddingValues(
         horizontal = 16.dp,
         vertical = 8.dp,
      ),
      textStyle = TextStyle(
         fontSize = 16.sp,
         lineHeight = 1.3.em,
      ),
      placeholder = {
         Text(
            text = "Enter your email...",
            modifier = Modifier.background(Color.Red.copy(0.2f))
         )
      },
      indicator = {
         FTextFieldIndicatorOutline()
      },
      trailingIcon = {
         FTextFieldIconClear()
      }
   )
}

@Composable
private fun SampleMaxLength(
   modifier: Modifier = Modifier,
) {
   val state = rememberTextFieldState()

   FTextField(
      modifier = modifier,
      state = state,
      indicator = {
         FTextFieldIndicatorOutline()
      },
   )

   LaunchedEffect(state) {
      state.fMaxLength(10)
   }
}

@Preview
@Composable
private fun Preview() {
   Content()
}