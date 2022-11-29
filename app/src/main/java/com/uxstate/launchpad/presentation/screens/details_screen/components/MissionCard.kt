package com.uxstate.launchpad.presentation.screens.details_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.uxstate.launchpad.R
import com.uxstate.launchpad.domain.model.*
import com.uxstate.launchpad.presentation.ui.theme.LaunchPadTheme
import com.uxstate.launchpad.util.LocalSpacing

@Composable
fun MissionCard(launch: Launch, modifier: Modifier = Modifier) {

    val spacing = LocalSpacing.current

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(spacing.spaceExtraSmall)
    ) {

        Column() {
            Text(
                text = stringResource(R.string.mission_label),
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = launch.mission.description,
                style = MaterialTheme.typography.body1,

            )
        }
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

    MissionCard(launch = launch)
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

        MissionCard(launch = launch)
    }
}