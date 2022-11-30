package com.uxstate.launchpad.presentation.screens.details_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.uxstate.launchpad.R
import com.uxstate.launchpad.domain.model.*
import com.uxstate.launchpad.util.LocalSpacing

@Composable
fun DetailsTopBar(launch: Launch, modifier: Modifier = Modifier, onClickBackArrow: () -> Unit) {
    val spacing = LocalSpacing.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .height(spacing.spaceExtraLarge)
            .fillMaxWidth()
            .padding(spacing.spaceSmall)
    ) {

        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = stringResource(R.string.back_label),
            modifier = Modifier.clickable {
                onClickBackArrow()
            }
        )
        Text(
            text = launch.name,
            style = MaterialTheme.typography.h6,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun TopBarPreview() {

    val launch = Launch(
        id = 0,
        name = "Falcon 9",
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
    DetailsTopBar(launch = launch, onClickBackArrow = {})
}