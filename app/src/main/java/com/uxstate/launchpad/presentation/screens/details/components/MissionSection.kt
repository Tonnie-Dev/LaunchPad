package com.uxstate.launchpad.presentation.screens.details.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.uxstate.launchpad.R
import com.uxstate.launchpad.domain.model.*
import com.uxstate.launchpad.utils.LocalSpacing
import com.uxstate.launchpad.utils.generateLaunch

@Composable
fun MissionSection(
    launch: Launch,
    modifier: Modifier = Modifier,
) {
    val spacing = LocalSpacing.current

    Column(
        modifier = modifier.padding(spacing.spaceSmall),
        verticalArrangement = Arrangement.SpaceAround,
    ) {
        Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))
        Text(
            text = stringResource(R.string.mission_label),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))
        Text(
            text = launch.mission.description,
            style = MaterialTheme.typography.bodyMedium,
        )
        Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))

        Spacer(modifier = Modifier.height(spacing.spaceLarge))

        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = spacing.spaceSingleDp,
            color = MaterialTheme.colorScheme.onSurface,
        )

        Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))
    }
}

@PreviewLightDark
@Composable
fun MissionCardPreview() {
    MissionSection(launch = generateLaunch())
}
