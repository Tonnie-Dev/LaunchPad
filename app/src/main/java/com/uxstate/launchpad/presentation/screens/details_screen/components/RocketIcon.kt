package com.uxstate.launchpad.presentation.screens.details_screen.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight

@Composable
fun RocketIcon(itemText: String, @DrawableRes icon: Int, modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {

        Icon(painter = painterResource(id = icon), contentDescription = itemText)
        Text(
            text = itemText,
            style = MaterialTheme.typography.caption,
            fontWeight = FontWeight.Black
        )
    }
}