package com.uxstate.launchpad.presentation.screens.common



import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
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
import java.net.ConnectException
import java.net.SocketTimeoutException

//default value added to enable us call this screen even if there is no error

//Add heroes:LazyPagingItems which has a convenient function to refresh data
@Composable
fun EmptyScreen(
    error: LoadState.Error? = null,
    heroes: LazyPagingItems<Hero>? = null
) {


    var message by remember {

        //default value
        mutableStateOf("Find Your Favorite Hero!")
    }

    var icon = remember {
        R.drawable.ic_search_document
    }


    if (error != null) {

        //in case of an error
        message = parseErrorMessage(error)
        icon = R.drawable.ic_network_error
    }

    var start by remember {
        //make it observable to trigger recomposition
        mutableStateOf(false)


    }

    LaunchedEffect(key1 = start, block = {
        start = true
    })

    val alphaValue by
    animateFloatAsState(
            targetValue = if (start) ContentAlpha.disabled else 0f,
            animationSpec = tween(1000)
    )

    EmptyContent(alphaValue, icon, message, heroes = heroes, error = error)

}

@Composable
fun EmptyContent(
    alphaValue: Float,
    @DrawableRes icon: Int,
    message: String,
    heroes: LazyPagingItems<Hero>? = null,
    error: LoadState.Error? = null
) {

    val spacing = LocalSpacing.current
    var isRefreshing by remember {
        mutableStateOf(false)
    }

    SwipeRefresh(state = rememberSwipeRefreshState(isRefreshing = isRefreshing),

            //SwipeRefresh will be visible only if the error is not null
            swipeEnabled = error != null,
            /*In this lambda we need to invalidate data*/
            onRefresh = {

                //first set the value of isRefreshing to true
                isRefreshing = true

                //call refresh() off the LazyPagingItem

                heroes?.refresh()

                //reset is refreshing to false

                isRefreshing = false


            }) {

        /*Column not scrollable by default there we add verticalScroll modifier*/
        Column(
                modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(state = rememberScrollState()),

                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
        ) {

            //Icon

            Icon(
                    modifier = Modifier
                            .size(spacing.spaceOneHundredFifty)
                            .alpha(alphaValue),
                    painter = painterResource(id = icon),
                    contentDescription = stringResource(R.string.network_error_icon),
                    tint = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray
            )

            //Text

            Text(
                    modifier = Modifier
                            .padding(top = spacing.spaceSmall)
                            .alpha(alphaValue),
                    text = message,
                    color = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray,
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
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
    EmptyContent(
            alphaValue = ContentAlpha.disabled,
            icon = R.drawable.ic_network_error,
            message = stringResource(
                    id = R.string.network_error_icon
            )
    )
}

@Preview(name = "Dark", showBackground = false, uiMode = UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
fun EmptyScreenPreviewDark() {

    EmptyContent(
            alphaValue = ContentAlpha.disabled,
            icon = R.drawable.ic_network_error,
            message = stringResource(
                    id = R.string.network_error_icon
            )
    )
}