package com.uxstate.launchpad.presentation.screens.details.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.uxstate.launchpad.R
import com.uxstate.launchpad.domain.model.*
import com.uxstate.launchpad.presentation.ui.theme.LaunchPadTheme
import com.uxstate.launchpad.utils.LocalSpacing

@Composable
fun MissionSection(launch: Launch, modifier: Modifier = Modifier) {

    val spacing = LocalSpacing.current

    Column(
            modifier = modifier.padding(spacing.spaceSmall),
            verticalArrangement = Arrangement.SpaceAround
    ) {
        Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))
        Text(
                text = stringResource(R.string.mission_label),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))
        Text(
                text = launch.mission.description,
                style = MaterialTheme.typography.bodyMedium,

                )
        Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))

        Spacer(modifier = Modifier.height(spacing.spaceLarge))

        Divider(
                modifier = Modifier.fillMaxWidth(),
                thickness = spacing.spaceSingleDp,
                color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))
    }
}

@Preview
@Composable
fun MissionCardPreviewLight() {
    val launch = Launch(
            id = 0,
            name = "",
            mission = Mission(
                    name = "My Mission",
                    description = """
Lorem ipsum dolor sit amet consectetur adipisicing elit. Maxime mollitia,
molestiae quas vel sint commodi repudiandae consequuntur voluptatum laborum
numquam blanditiis harum quisquam eius 
                        
            """.trimIndent(),
                    type = ""
            ),
            imageUrl = "",
            provider = Provider(id = 0, name = "", type = ""),
            status = Status(name = "Name", abbrev = "TBD", description = ""),
            pad = Pad(
                    locationName = "",
                    latitude = "",
                    longitude = "", complex = "",
                    totalLaunchCount = 0,
                    totalLandingCount = 0,

                    ),
            startWindowDate = "",
            rocket = Rocket(name = "", family = "")
    )

    MissionSection(launch = launch)
}

@Preview
@Composable
fun MissionCardPreviewDark() {

    LaunchPadTheme() {

        val launch = Launch(
                id = 0,
                name = "",
                mission = Mission(
                        name = "My Mission",
                        description = """
Lorem ipsum dolor sit amet consectetur adipisicing elit. Maxime mollitia,
molestiae quas vel sint commodi repudiandae consequuntur voluptatum laborum
numquam blanditiis harum quisquam eius 
                        
                """.trimIndent(),
                        type = ""
                ),
                imageUrl = "",
                provider = Provider(id = 0, name = "", type = ""),
                status = Status(name = "Name", abbrev = "TBD", description = ""),
                pad = Pad(
                        locationName = "",
                        latitude = "",
                        longitude = "", complex = "",
                        totalLaunchCount = 0,
                        totalLandingCount = 0
                ),
                startWindowDate = "",
                rocket = Rocket(name = "", family = "")
        )

        MissionSection(launch = launch)
    }
}
