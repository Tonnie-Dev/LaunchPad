package com.uxstate.launchpad.presentation.screens.common

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.uxstate.launchpad.R
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.presentation.ui.theme.LaunchPadTheme
import com.uxstate.launchpad.utils.LocalSpacing
import java.net.ConnectException
import java.net.SocketTimeoutException

// default value added to enable us call this screen even if there is no error

// Add heroes:LazyPagingItems which has a convenient function to refresh data
@Composable
fun EmptyScreen(
    error: LoadState.Error? = null,
    launches: LazyPagingItems<Launch>? = null
) {

    var message by remember {

        // default value
        mutableStateOf("Rocket Launches")
    }

    var icon = remember {
        R.drawable.search_icon
    }

    if (error != null) {

        // in case of an error
        message = parseErrorMessage(error)
        icon = R.drawable.network_error_icon
    }

    var start by remember {
        // make it observable to trigger recomposition
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = start, block = {
        start = true
    })

    val alphaValue by
    animateFloatAsState(
            targetValue = if (start) 0.38f else 0f,
            animationSpec = tween(1000, easing = FastOutSlowInEasing), label = ""
    )

    EmptyContent(alphaValue, icon, message, launches = launches, error = error)
}

@Composable
fun EmptyContent(
    alphaValue: Float,
    @DrawableRes icon: Int,
    message: String,
    launches: LazyPagingItems<Launch>? = null,
    error: LoadState.Error? = null
) {

    val spacing = LocalSpacing.current
    var isRefreshing by remember {
        mutableStateOf(false)
    }

    SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = isRefreshing),

            // SwipeRefresh will be visible only if the error is not null
            swipeEnabled = error != null,
            /*In this lambda we need to invalidate data*/
            onRefresh = {

                // first set the value of isRefreshing to true
                isRefreshing = true

                // call refresh() off the LazyPagingItem

                launches?.refresh()

                // reset is refreshing to false

                isRefreshing = false
            }
    ) {

        /*Column not scrollable by default there we add verticalScroll modifier*/
        Column(
                modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(state = rememberScrollState()),

                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
        ) {

            // Icon

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
    }
}

fun parseErrorMessage(loadStateError: LoadState.Error): String {

    return when (loadStateError.error) {

        is SocketTimeoutException -> {
            "Server Unavailable"
        }

        is ConnectException -> {
            "Internet Unavailable"
        }

        else -> "Unknown Error"
    }
}

@Preview(name = "Light", showBackground = true, uiMode = UI_MODE_NIGHT_NO, showSystemUi = true)
@Composable
fun EmptyScreenPreviewLight() {

    LaunchPadTheme() {
        EmptyContent(
                alphaValue = 0.38f,
                icon = R.drawable.network_error_icon,
                message = stringResource(
                        id = R.string.dialog_error_title
                )
        )
    }
}

@Preview(name = "Dark", showBackground = false, uiMode = UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
fun EmptyScreenPreviewDark() {

    LaunchPadTheme() {
        EmptyContent(
                alphaValue = 0.38f,
                icon = R.drawable.search_icon,
                message = stringResource(
                        id = R.string.dialog_error_title
                )
        )
    }
}
