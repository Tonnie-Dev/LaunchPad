package com.uxstate.launchpad.presentation.common

/*

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.DialogProperties
import com.uxstate.launchpad.R
import com.uxstate.launchpad.utils.LocalSpacing

@OptIn(ExperimentalMaterial3Api::class)
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
    val textPaddingAll = (spacing.spaceMedium + spacing.spaceSmall)
    val buttonPaddingAll = spacing.spaceSmall
    if (dialogState) {

        AlertDialog(
            onDismissRequest = {
                onDialogStateChange?.invoke(false)
                onDismiss?.invoke()
            },
            title = null,
            text = null,
            buttons = {

                Column {
                    Image(
                        painter = painterResource(id = R.drawable.error_icon),
                        contentDescription = "",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.size(spacing.spaceLarge)
                    )

                    Row(modifier = Modifier.padding(all = textPaddingAll)) {

                        Text(text = stringResource(R.string.dialog_error_text))
                    }

                    HorizontalDivider(
                            thickness = spacing.spaceSingleDp,
                            color = MaterialTheme.colorScheme.onSurface
                    )

                    Row(
                        modifier = Modifier.padding(all = buttonPaddingAll),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        TextButton(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                onDialogStateChange?.invoke(false)
                                onDialogPositiveButton?.invoke()
                            }
                        ) {
                            Text(
                                text = stringResource(R.string.dialog_ok),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            ),
            modifier = modifier,
            shape = dialogShape
        )
    }
}
@Preview
@Composable
fun AppDialogPreview() {
}*/
