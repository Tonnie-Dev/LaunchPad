package com.uxstate.launchpad.presentation.screens.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.uxstate.launchpad.R
import com.uxstate.launchpad.util.LocalSpacing

@Composable
fun SimpleAlertDialog(
    isShowDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {

    val spacing = LocalSpacing.current
    if (isShowDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            buttons = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextButton(onClick = onConfirm) {
                        Text(text = stringResource(id = R.string.dialog_ok))
                    }
                }
            },
            title = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = stringResource(R.string.dialog_error_title))
                }
            },
            text = {
                Text(text = stringResource(id = R.string.dialog_error_text))
            }
        )
    }
}

@Preview
@Composable
fun SimpleAlertDialogPreview() {

    SimpleAlertDialog(isShowDialog = true, onDismiss = { }, onConfirm = {})
}