package com.assem.albumsapp.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.webkit.URLUtil

/**
 * Created by mohamedassem
 * mohamed.assem.ali@gmail.com
 */

fun Context.startBrowserIntent(url: String) {
    if (!URLUtil.isValidUrl(url)) {
        return
    }
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(url)
    this.startActivity(intent)
}