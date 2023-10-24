package com.abkhrr.gazego.core.designsystem.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import com.abkhrr.gazego.core.designsystem.icon.GazegoIcon
import com.abkhrr.gazego.core.designsystem.theme.GazegoTheme

@Composable
fun GazegoTextField(
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes placeholderResourceId: Int,
    @DrawableRes iconResourceId: Int,
    modifier: Modifier = Modifier,
    placeholder: @Composable (() -> Unit) = {
        Text(text = stringResource(id = placeholderResourceId))
    },
    leadingIcon: @Composable (() -> Unit) = {
        GazegoIcon(
            iconResourceId = iconResourceId,
            contentDescription = stringResource(id = placeholderResourceId)
        )
    },
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    singleLine: Boolean = true,
    shape: Shape = GazegoTheme.shapes.extraMedium,
    colors: TextFieldColors = TextFieldDefaults.colors(
        focusedTextColor = GazegoTheme.colors.white,
        unfocusedTextColor = GazegoTheme.colors.white,
        cursorColor = GazegoTheme.colors.primaryBlue,
        selectionColors = TextSelectionColors(
            handleColor = GazegoTheme.colors.primaryBlue,
            backgroundColor = GazegoTheme.colors.primaryBlue.copy(
                alpha = 0.4f
            )
        ),
        focusedContainerColor = GazegoTheme.colors.primarySoft,
        unfocusedContainerColor = GazegoTheme.colors.primarySoft,
        focusedLeadingIconColor = GazegoTheme.colors.grey,
        unfocusedLeadingIconColor = GazegoTheme.colors.grey,
        focusedTrailingIconColor = GazegoTheme.colors.grey,
        unfocusedTrailingIconColor = GazegoTheme.colors.grey,
        focusedPlaceholderColor = GazegoTheme.colors.grey,
        unfocusedPlaceholderColor = GazegoTheme.colors.grey,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent
    )
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        shape = shape,
        colors = colors
    )
}