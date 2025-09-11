package com.sd.lib.compose.input

import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class FTextFieldColors(
  val unfocusedContainerColor: Color,
  val focusedContainerColor: Color,
  val disabledContainerColor: Color,
  val errorContainerColor: Color,

  val unfocusedTextColor: Color,
  val focusedTextColor: Color,
  val disabledTextColor: Color,
  val errorTextColor: Color,

  val unfocusedIndicatorColor: Color,
  val focusedIndicatorColor: Color,
  val disabledIndicatorColor: Color,
  val errorIndicatorColor: Color,

  val unfocusedPlaceholderColor: Color,
  val focusedPlaceholderColor: Color,
  val disabledPlaceholderColor: Color,
  val errorPlaceholderColor: Color,

  val cursorColor: Color,
  val errorCursorColor: Color,
  val textSelectionColors: TextSelectionColors,

  val unfocusedLeadingIconColor: Color,
  val focusedLeadingIconColor: Color,
  val disabledLeadingIconColor: Color,
  val errorLeadingIconColor: Color,

  val unfocusedTrailingIconColor: Color,
  val focusedTrailingIconColor: Color,
  val disabledTrailingIconColor: Color,
  val errorTrailingIconColor: Color,
)

internal fun FTextFieldColors.containerColor(
  enabled: Boolean,
  isError: Boolean,
  focused: Boolean,
): Color = when {
  !enabled -> disabledContainerColor
  isError -> errorContainerColor
  focused -> focusedContainerColor
  else -> unfocusedContainerColor
}

internal fun FTextFieldColors.textColor(
  enabled: Boolean,
  isError: Boolean,
  focused: Boolean,
): Color = when {
  !enabled -> disabledTextColor
  isError -> errorTextColor
  focused -> focusedTextColor
  else -> unfocusedTextColor
}

internal fun FTextFieldColors.indicatorColor(
  enabled: Boolean,
  isError: Boolean,
  focused: Boolean,
): Color = when {
  !enabled -> disabledIndicatorColor
  isError -> errorIndicatorColor
  focused -> focusedIndicatorColor
  else -> unfocusedIndicatorColor
}

internal fun FTextFieldColors.placeholderColor(
  enabled: Boolean,
  isError: Boolean,
  focused: Boolean,
): Color = when {
  !enabled -> disabledPlaceholderColor
  isError -> errorPlaceholderColor
  focused -> focusedPlaceholderColor
  else -> unfocusedPlaceholderColor
}

internal fun FTextFieldColors.cursorColor(isError: Boolean): Color {
  return if (isError) errorCursorColor else cursorColor
}

internal fun FTextFieldColors.leadingIconColor(
  enabled: Boolean,
  isError: Boolean,
  focused: Boolean,
): Color = when {
  !enabled -> disabledLeadingIconColor
  isError -> errorLeadingIconColor
  focused -> focusedLeadingIconColor
  else -> unfocusedLeadingIconColor
}

internal fun FTextFieldColors.trailingIconColor(
  enabled: Boolean,
  isError: Boolean,
  focused: Boolean,
): Color = when {
  !enabled -> disabledTrailingIconColor
  isError -> errorTrailingIconColor
  focused -> focusedTrailingIconColor
  else -> unfocusedTrailingIconColor
}