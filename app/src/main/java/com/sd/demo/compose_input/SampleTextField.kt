package com.sd.demo.compose_input

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sd.demo.compose_input.ui.theme.AppTheme
import com.sd.lib.compose.input.FTextField
import com.sd.lib.compose.input.FTextFieldIconClear
import com.sd.lib.compose.input.FTextFieldIndicatorOutline

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
      SampleIndicator()
      SampleCenter()
      SampleCustom()
   }
}

@Composable
private fun SampleIndicator(
   modifier: Modifier = Modifier,
) {
   val state = rememberTextFieldState()
   FTextField(
      modifier = modifier,
      state = state,
      indicator = { FTextFieldIndicatorOutline() }
   )
}

@Composable
private fun SampleCenter(
   modifier: Modifier = Modifier,
) {
   val state = rememberTextFieldState()

   FTextField(
      modifier = modifier.height(80.dp),
      state = state,
      contentAlignment = Alignment.CenterVertically,
      contentPadding = PaddingValues(0.dp),
      textStyle = LocalTextStyle.current.copy(
         textAlign = TextAlign.Center,
      ),
      placeholder = {
         Text(
            text = "placeholder",
            modifier = Modifier.background(Color.Red)
         )
      },
      indicator = { FTextFieldIndicatorOutline() },
      trailingIcon = { FTextFieldIconClear() },
   )
}

@Composable
private fun SampleCustom(
   modifier: Modifier = Modifier,
) {
   val state = rememberTextFieldState()

   FTextField(
      modifier = modifier.height(100.dp),
      state = state,
      maxLines = Int.MAX_VALUE,
      contentAlignment = Alignment.Top,
      placeholder = {
         Text(
            text = "input",
            fontSize = 12.sp,
         )
      },
      indicator = { FTextFieldIndicatorOutline() },
   )
}