package com.uxstate.launchpad.presentation.screens.details.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.uxstate.launchpad.R
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.utils.LocalSpacing

@Composable
fun StatusSection(
    launch: Launch,
    modifier: Modifier = Modifier,
) {
    val spacing = LocalSpacing.current
    Column(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(spacing.spaceSmall),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))

        Text(
            text = stringResource(R.string.status_label),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Start),
        )
        Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))

        Text(
            text = launch.status.description,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(spacing.spaceLarge))

        Divider(
            modifier = Modifier.fillMaxWidth(),
            thickness = spacing.spaceSingleDp,
            color = MaterialTheme.colorScheme.onSurface,
        )

        Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))
    }
}
