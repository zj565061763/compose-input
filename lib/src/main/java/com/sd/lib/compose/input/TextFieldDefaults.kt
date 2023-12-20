/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

        cursorColor: Color = MaterialTheme.colorScheme.primary,
        errorCursorColor: Color = errorTextColor,

        selectionColors: TextSelectionColors = TextSelectionColors(
            handleColor = cursorColor,
            backgroundColor = cursorColor.copy(alpha = 0.4f)
        ),

        focusedIndicatorColor: Color = MaterialTheme.colorScheme.primary,
        unfocusedIndicatorColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
        disabledIndicatorColor: Color = disabledTextColor,
        errorIndicatorColor: Color = errorTextColor,

        focusedLeadingIconColor: Color = focusedTextColor,
        unfocusedLeadingIconColor: Color = unfocusedTextColor,
        disabledLeadingIconColor: Color = disabledTextColor,
        errorLeadingIconColor: Color = errorTextColor,

        focusedTrailingIconColor: Color = focusedTextColor,
        unfocusedTrailingIconColor: Color = unfocusedTextColor,
        disabledTrailingIconColor: Color = disabledTextColor,
        errorTrailingIconColor: Color = errorTextColor,

        focusedLabelColor: Color = focusedIndicatorColor,
        unfocusedLabelColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
        disabledLabelColor: Color = disabledTextColor,
        errorLabelColor: Color = errorTextColor,

        focusedPlaceholderColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
        unfocusedPlaceholderColor: Color = focusedPlaceholderColor,
        disabledPlaceholderColor: Color = focusedPlaceholderColor,
        errorPlaceholderColor: Color = focusedPlaceholderColor,

        focusedSupportingTextColor: Color = focusedTextColor,
        unfocusedSupportingTextColor: Color = unfocusedTextColor,
        disabledSupportingTextColor: Color = disabledTextColor,
        errorSupportingTextColor: Color = errorTextColor,

        focusedPrefixColor: Color = focusedTextColor,
        unfocusedPrefixColor: Color = unfocusedTextColor,
        disabledPrefixColor: Color = disabledTextColor,
        errorPrefixColor: Color = errorTextColor,

        focusedSuffixColor: Color = focusedTextColor,
        unfocusedSuffixColor: Color = unfocusedTextColor,
        disabledSuffixColor: Color = disabledTextColor,
        errorSuffixColor: Color = errorTextColor,
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

            focusedLeadingIconColor = focusedLeadingIconColor,
            unfocusedLeadingIconColor = unfocusedLeadingIconColor,
            disabledLeadingIconColor = disabledLeadingIconColor,
            errorLeadingIconColor = errorLeadingIconColor,

            focusedTrailingIconColor = focusedTrailingIconColor,
            unfocusedTrailingIconColor = unfocusedTrailingIconColor,
            disabledTrailingIconColor = disabledTrailingIconColor,
            errorTrailingIconColor = errorTrailingIconColor,

            focusedLabelColor = focusedLabelColor,
            unfocusedLabelColor = unfocusedLabelColor,
            disabledLabelColor = disabledLabelColor,
            errorLabelColor = errorLabelColor,

            focusedPlaceholderColor = focusedPlaceholderColor,
            unfocusedPlaceholderColor = unfocusedPlaceholderColor,
            disabledPlaceholderColor = disabledPlaceholderColor,
            errorPlaceholderColor = errorPlaceholderColor,

            focusedSupportingTextColor = focusedSupportingTextColor,
            unfocusedSupportingTextColor = unfocusedSupportingTextColor,
            disabledSupportingTextColor = disabledSupportingTextColor,
            errorSupportingTextColor = errorSupportingTextColor,

            focusedPrefixColor = focusedPrefixColor,
            unfocusedPrefixColor = unfocusedPrefixColor,
            disabledPrefixColor = disabledPrefixColor,
            errorPrefixColor = errorPrefixColor,

            focusedSuffixColor = focusedSuffixColor,
            unfocusedSuffixColor = unfocusedSuffixColor,
            disabledSuffixColor = disabledSuffixColor,
            errorSuffixColor = errorSuffixColor,
        )
    }
}