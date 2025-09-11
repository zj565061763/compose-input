package com.sd.lib.compose.input

import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse

object FTextFieldDefaults {
  @Suppress("NAME_SHADOWING")
  @Composable
  fun colors(
    // Container
    unfocusedContainerColor: Color = Color.Unspecified,
    focusedContainerColor: Color = Color.Unspecified,
    disabledContainerColor: Color = Color.Unspecified,
    errorContainerColor: Color = Color.Unspecified,

    // Text
    unfocusedTextColor: Color = Color.Unspecified,
    focusedTextColor: Color = Color.Unspecified,
    disabledTextColor: Color = Color.Unspecified,
    errorTextColor: Color = Color.Unspecified,

    // Indicator
    unfocusedIndicatorColor: Color = Color.Unspecified,
    focusedIndicatorColor: Color = Color.Unspecified,
    disabledIndicatorColor: Color = Color.Unspecified,
    errorIndicatorColor: Color = Color.Unspecified,

    // Placeholder
    unfocusedPlaceholderColor: Color = Color.Unspecified,
    focusedPlaceholderColor: Color = Color.Unspecified,
    disabledPlaceholderColor: Color = Color.Unspecified,
    errorPlaceholderColor: Color = Color.Unspecified,

    // Cursor
    cursorColor: Color = Color.Unspecified,
    errorCursorColor: Color = Color.Unspecified,

    // Leading
    unfocusedLeadingIconColor: Color = Color.Unspecified,
    focusedLeadingIconColor: Color = Color.Unspecified,
    disabledLeadingIconColor: Color = Color.Unspecified,
    errorLeadingIconColor: Color = Color.Unspecified,

    // Trailing
    unfocusedTrailingIconColor: Color = Color.Unspecified,
    focusedTrailingIconColor: Color = Color.Unspecified,
    disabledTrailingIconColor: Color = Color.Unspecified,
    errorTrailingIconColor: Color = Color.Unspecified,
  ): FTextFieldColors {
    val unfocusedTextColor = unfocusedTextColor.takeOrElse { MaterialTheme.colorScheme.onSurface }
    val focusedTextColor = focusedTextColor.takeOrElse { unfocusedTextColor }
    val disabledTextColor = disabledTextColor.takeOrElse { unfocusedTextColor.copy(alpha = 0.3f) }
    val errorTextColor = errorTextColor.takeOrElse { MaterialTheme.colorScheme.error }

    val unfocusedIndicatorColor = unfocusedIndicatorColor.takeOrElse { MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f) }
    val focusedIndicatorColor = focusedIndicatorColor.takeOrElse { MaterialTheme.colorScheme.primary }
    val disabledIndicatorColor = disabledIndicatorColor.takeOrElse { disabledTextColor }
    val errorIndicatorColor = errorIndicatorColor.takeOrElse { errorTextColor }

    val unfocusedPlaceholderColor = unfocusedPlaceholderColor.takeOrElse { MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f) }

    val cursorColor = cursorColor.takeOrElse { focusedIndicatorColor }
    return FTextFieldColors(
      // Container
      unfocusedContainerColor = unfocusedContainerColor.takeOrElse { Color.Transparent },
      focusedContainerColor = focusedContainerColor.takeOrElse { Color.Transparent },
      disabledContainerColor = disabledContainerColor.takeOrElse { Color.Transparent },
      errorContainerColor = errorContainerColor.takeOrElse { Color.Transparent },

      // Text
      unfocusedTextColor = unfocusedTextColor,
      focusedTextColor = focusedTextColor,
      disabledTextColor = disabledTextColor,
      errorTextColor = errorTextColor,

      // Indicator
      unfocusedIndicatorColor = unfocusedIndicatorColor,
      focusedIndicatorColor = focusedIndicatorColor,
      disabledIndicatorColor = disabledIndicatorColor,
      errorIndicatorColor = errorIndicatorColor,

      // Placeholder
      unfocusedPlaceholderColor = unfocusedPlaceholderColor,
      focusedPlaceholderColor = focusedPlaceholderColor.takeOrElse { unfocusedPlaceholderColor },
      disabledPlaceholderColor = disabledPlaceholderColor.takeOrElse { unfocusedPlaceholderColor },
      errorPlaceholderColor = errorPlaceholderColor.takeOrElse { unfocusedPlaceholderColor },

      // Cursor
      cursorColor = cursorColor,
      errorCursorColor = errorCursorColor.takeOrElse { errorTextColor },
      textSelectionColors = TextSelectionColors(
        handleColor = cursorColor,
        backgroundColor = cursorColor.copy(alpha = 0.4f)
      ),

      // Leading
      unfocusedLeadingIconColor = unfocusedLeadingIconColor.takeOrElse { unfocusedTextColor },
      focusedLeadingIconColor = focusedLeadingIconColor.takeOrElse { focusedTextColor },
      disabledLeadingIconColor = disabledLeadingIconColor.takeOrElse { disabledTextColor },
      errorLeadingIconColor = errorLeadingIconColor.takeOrElse { errorTextColor },

      // Trailing
      unfocusedTrailingIconColor = unfocusedTrailingIconColor.takeOrElse { unfocusedTextColor },
      focusedTrailingIconColor = focusedTrailingIconColor.takeOrElse { focusedTextColor },
      disabledTrailingIconColor = disabledTrailingIconColor.takeOrElse { disabledTextColor },
      errorTrailingIconColor = errorTrailingIconColor.takeOrElse { errorTextColor },
    )
  }
}