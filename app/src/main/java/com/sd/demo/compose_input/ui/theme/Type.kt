package com.sd.demo.compose_input.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

val Typography = run {
   val default = Typography()
   val defaultLineHeight = (1.0).em
   val defaultLetterSpacing = (0.0).sp
   Typography(
      displayLarge = default.displayLarge.copy(
         lineHeight = defaultLineHeight,
         letterSpacing = defaultLetterSpacing,
      ),
      displayMedium = default.displayMedium.copy(
         lineHeight = defaultLineHeight,
         letterSpacing = defaultLetterSpacing,
      ),
      displaySmall = default.displaySmall.copy(
         lineHeight = defaultLineHeight,
         letterSpacing = defaultLetterSpacing,
      ),

      headlineLarge = default.headlineLarge.copy(
         lineHeight = defaultLineHeight,
         letterSpacing = defaultLetterSpacing,
      ),
      headlineMedium = default.headlineMedium.copy(
         lineHeight = defaultLineHeight,
         letterSpacing = defaultLetterSpacing,
      ),
      headlineSmall = default.headlineSmall.copy(
         lineHeight = defaultLineHeight,
         letterSpacing = defaultLetterSpacing,
      ),

      titleLarge = default.titleLarge.copy(
         lineHeight = defaultLineHeight,
         letterSpacing = defaultLetterSpacing,
      ),
      titleMedium = default.titleMedium.copy(
         lineHeight = defaultLineHeight,
         letterSpacing = defaultLetterSpacing,
      ),
      titleSmall = default.titleSmall.copy(
         lineHeight = defaultLineHeight,
         letterSpacing = defaultLetterSpacing,
      ),

      bodyLarge = default.bodyLarge.copy(
         lineHeight = defaultLineHeight,
         letterSpacing = defaultLetterSpacing,
      ),
      bodyMedium = default.bodyMedium.copy(
         lineHeight = defaultLineHeight,
         letterSpacing = defaultLetterSpacing,
      ),
      bodySmall = default.bodySmall.copy(
         lineHeight = defaultLineHeight,
         letterSpacing = defaultLetterSpacing,
      ),

      labelLarge = default.labelLarge.copy(
         lineHeight = defaultLineHeight,
         letterSpacing = defaultLetterSpacing,
         fontWeight = FontWeight.Medium,
      ),
      labelMedium = default.labelMedium.copy(
         lineHeight = defaultLineHeight,
         letterSpacing = defaultLetterSpacing,
      ),
      labelSmall = default.labelSmall.copy(
         lineHeight = defaultLineHeight,
         letterSpacing = defaultLetterSpacing,
      ),
   )
}