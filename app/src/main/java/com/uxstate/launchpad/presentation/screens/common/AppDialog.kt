package com.uxstate.launchpad.presentation.screens.common

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.uxstate.launchpad.util.LocalSpacing

@Composable
fun AppDialog(
    modifier: Modifier = Modifier,
    dialogState: Boolean = false,
    onDialogPositiveButton: (() -> Unit)? = null,
    onDialogStateChange: ((Boolean) -> Unit)? = null,
    onDismiss: (() -> Unit)? = null
) {

    val spacing = LocalSpacing.current
    val dialogShape = RoundedCornerShape(spacing.spaceMedium)

    if (dialogState) {

        AlertDialog(
            onDismissRequest = {
                onDialogStateChange?.invoke(false)
                onDismiss?.invoke()
            },
            title = { Text(text = "Error") },
            text = { Text(text = "Launch not found!") }, buttons = {}
        )
    }
}

@Preview
@Composable
fun AppDialogPreview() {

    AppDialog()
}