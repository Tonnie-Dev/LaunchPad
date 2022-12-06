package com.uxstate.launchpad.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri

fun openGoogleMap(latitude: Double, longitude: Double, context: Context) {

    // you need action, data and package name

    val uri = Uri.parse("geo:$latitude,$longitude?z=15")
    // create an intent
    val mapIntent = Intent()

    // add action & data
    mapIntent.action = Intent.ACTION_VIEW
    mapIntent.data = uri

    // package name for google maps app
    mapIntent.setPackage("com.google.android.apps.maps")
    try {
        context.startActivity(mapIntent)
    } catch (e: ActivityNotFoundException) {

        // if map app isn't resolved/installed catch error
        e.printStackTrace()
    }
}