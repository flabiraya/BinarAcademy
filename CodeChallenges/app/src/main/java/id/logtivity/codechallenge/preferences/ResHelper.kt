package id.logtivity.codechallenge.preferences

import android.content.Context

fun Context.getStringResources(stringId: Int): String {
    return this.resources.getString(stringId)
}