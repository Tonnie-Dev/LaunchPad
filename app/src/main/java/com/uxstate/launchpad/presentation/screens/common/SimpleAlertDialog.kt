package com.uxstate.launchpad.presentation.screens.common

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.uxstate.launchpad.R

@Composable
fun SimpleAlertDialog() {

    AlertDialog(onDismissRequest = { },
            buttons = {
                TextButton(onClick = { }) {
                    Text(text = stringResource(id = R.string.dialog_ok))
                }
            },
            title = { Text(text = stringResource(R.string.dialog_error_title)) },
            text = { Text(text = stringResource(id = R.string.dialog_error_text)) })
}