package com.uxstate.launchpad.presentation.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.uxstate.launchpad.R
import com.uxstate.launchpad.presentation.ui.theme.LaunchPadTheme
import com.uxstate.launchpad.utils.LocalSpacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RefreshScreen(
    alphaValue: Float,
    message: String,
    @DrawableRes icon: Int,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier
) {

PullToRefreshBox(isRefreshing = isRefreshing, onRefresh = onRefresh, modifier = modifier) {

    RefreshContent(
            alphaValue = alphaValue,
            message = message,
            icon = icon,
            onRefresh = onRefresh,

            )
}


}


@Composable
fun RefreshContent(
    alphaValue: Float,
    message: String,
    @DrawableRes icon: Int,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current

    Column(
            modifier = modifier
                    .fillMaxSize()
                    .verticalScroll(state = rememberScrollState())
                    .padding(spacing.spaceSmall),

            horizontalAlignment = Alignment.CenterHorizontally


    ) {

        Column(
                modifier = Modifier.weight(.8f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                    modifier = Modifier
                            .size(spacing.spaceOneHundredFifty)
                            .alpha(alphaValue),
                    painter = painterResource(id = icon),
                    contentDescription = stringResource(R.string.dialog_error_title),
                    tint = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray
            )

            // Text

            Text(
                    modifier = Modifier
                            .padding(top = spacing.spaceSmall)
                            .alpha(alphaValue),
                    text = message,
                    color = Color.White,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
            )
        }


        Column(modifier = Modifier.weight(.2f)) {

            Button(onClick = onRefresh) {


                Row(
                        horizontalArrangement = Arrangement.spacedBy(spacing.spaceExtraSmall),
                        verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(text = stringResource(R.string.refresh_text))
                    Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "",
                            modifier = Modifier.size(spacing.spaceMedium)
                    )
                }


            }
        }


    }
}


@PreviewLightDark
@Composable
private fun RefreshContentPreview() {

    LaunchPadTheme {

        Surface {
            RefreshContent(
                    alphaValue = 0.38f,
                    message = "Network Error",
                    icon = R.drawable.network_error_icon,
                    onRefresh = {})
        }
    }

}