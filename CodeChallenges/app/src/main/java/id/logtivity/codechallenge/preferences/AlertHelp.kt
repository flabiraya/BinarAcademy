package id.logtivity.codechallenge.preferences

import android.content.Context
import android.widget.Toast
import org.jetbrains.anko.alert
import org.jetbrains.anko.yesButton


fun Context.alertHelper(tittle: String) {
    this.alert(tittle) {
        yesButton { it.dismiss() }
    }.show()
}

fun Context.myToast(tittle: String) {
    Toast.makeText(this, tittle, Toast.LENGTH_SHORT).show()
}