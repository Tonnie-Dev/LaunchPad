package com.uxstate.launchpad.presentation.screens.common


import androidx.annotation.DrawableRes
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.uxstate.launchpad.R
import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.presentation.common.RefreshScreen
import com.uxstate.launchpad.presentation.ui.theme.LaunchPadTheme
import java.net.ConnectException
import java.net.SocketTimeoutException

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
        icon = com.uxstate.launchpad.R.drawable.network_error_icon
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

    EmptyContent(alphaValue, icon, message, launches = launches)
}

@Composable
fun EmptyContent(
    alphaValue: Float,
    @DrawableRes icon: Int,
    message: String,
    launches: LazyPagingItems<Launch>? = null
) {

    var isRefreshing by remember {
        mutableStateOf(false)
    }

    RefreshScreen(
            alphaValue = alphaValue,
            message = message,
            icon = icon,
            isRefreshing = isRefreshing,
            onRefresh = {
                isRefreshing = true
                launches?.refresh()
                isRefreshing = false
            })
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

@Preview(showBackground = true)
@PreviewLightDark
@Composable
private fun EmptyContentPreview() {

    LaunchPadTheme {

        EmptyContent(
                alphaValue = 0.38f,
                icon = R.drawable.network_error_icon,
                message = stringResource(
                        id = R.string.dialog_error_text
                )
        )
    }

}



