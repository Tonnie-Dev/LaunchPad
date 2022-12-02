package com.uxstate.launchpad.presentation.screens.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.uxstate.launchpad.util.LocalSpacing

@Composable
fun AppDialog(
    modifier: Modifier = Modifier,
    dialogState: Boolean = false,
    onDialogPositiveButton: (() -> Unit)? = null,
    onDialogStateChange:((Boolean) -> Unit)? = null,
    onDismiss: (()-> Unit)? = null
) {

    val spacing = LocalSpacing.current
}