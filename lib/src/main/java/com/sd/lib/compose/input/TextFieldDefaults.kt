package com.sd.lib.compose.input

import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object FTextFieldDefaults {
    @Composable
    fun colors(
        focusedTextColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
        unfocusedTextColor: Color = focusedTextColor,
        disabledTextColor: Color = focusedTextColor.copy(0.3f),
        errorTextColor: Color = MaterialTheme.colorScheme.error,

        focusedContainerColor: Color = Color.Transparent,
        unfocusedContainerColor: Color = Color.Transparent,
        disabledContainerColor: Color = Color.Transparent,
        errorContainerColor: Color = Color.Transparent,

        focusedIndicatorColor: Color = MaterialTheme.colorScheme.primary,
        unfocusedIndicatorColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
        disabledIndicatorColor: Color = disabledTextColor,
        errorIndicatorColor: Color = errorTextColor,

        cursorColor: Color = focusedIndicatorColor,
        errorCursorColor: Color = errorTextColor,

        selectionColors: TextSelectionColors = TextSelectionColors(
            handleColor = cursorColor,
            backgroundColor = cursorColor.copy(alpha = 0.4f)
        ),

        focusedPlaceholderColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
        unfocusedPlaceholderColor: Color = focusedPlaceholderColor,
        disabledPlaceholderColor: Color = focusedPlaceholderColor,
        errorPlaceholderColor: Color = focusedPlaceholderColor,

        focusedLeadingIconColor: Color = focusedTextColor,
        unfocusedLeadingIconColor: Color = unfocusedTextColor,
        disabledLeadingIconColor: Color = disabledTextColor,
        errorLeadingIconColor: Color = errorTextColor,

        focusedTrailingIconColor: Color = focusedTextColor,
        unfocusedTrailingIconColor: Color = unfocusedTextColor,
        disabledTrailingIconColor: Color = disabledTextColor,
        errorTrailingIconColor: Color = errorTextColor,
    ): FTextFieldColors {
        return FTextFieldColors(
            focusedTextColor = focusedTextColor,
            unfocusedTextColor = unfocusedTextColor,
            disabledTextColor = disabledTextColor,
            errorTextColor = errorTextColor,

            focusedContainerColor = focusedContainerColor,
            unfocusedContainerColor = unfocusedContainerColor,
            disabledContainerColor = disabledContainerColor,
            errorContainerColor = errorContainerColor,

            cursorColor = cursorColor,
            errorCursorColor = errorCursorColor,

            textSelectionColors = selectionColors,

            focusedIndicatorColor = focusedIndicatorColor,
            unfocusedIndicatorColor = unfocusedIndicatorColor,
            disabledIndicatorColor = disabledIndicatorColor,
            errorIndicatorColor = errorIndicatorColor,

            focusedPlaceholderColor = focusedPlaceholderColor,
            unfocusedPlaceholderColor = unfocusedPlaceholderColor,
            disabledPlaceholderColor = disabledPlaceholderColor,
            errorPlaceholderColor = errorPlaceholderColor,

            focusedLeadingIconColor = focusedLeadingIconColor,
            unfocusedLeadingIconColor = unfocusedLeadingIconColor,
            disabledLeadingIconColor = disabledLeadingIconColor,
            errorLeadingIconColor = errorLeadingIconColor,

            focusedTrailingIconColor = focusedTrailingIconColor,
            unfocusedTrailingIconColor = unfocusedTrailingIconColor,
            disabledTrailingIconColor = disabledTrailingIconColor,
            errorTrailingIconColor = errorTrailingIconColor,
        )
    }
}