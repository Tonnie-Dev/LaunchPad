package com.uxstate.launchpad.presentation.screens.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.uxstate.launchpad.R
import com.uxstate.launchpad.utils.LocalSpacing

@OptIn(ExperimentalMaterial3Api::class)
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
                confirmButton = {

                    TextButton(onClick = onConfirm) {
                        Text(
                                text = stringResource(id = R.string.dialog_ok),
                                color = MaterialTheme.colorScheme.secondary
                        )
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

@PreviewLightDark
@Composable
fun SimpleAlertDialogPreview() {

    SimpleAlertDialog(isShowDialog = true, onDismiss = { }, onConfirm = {})
}
