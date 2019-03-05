package id.logtivity.codechallenge.preferences

import android.app.Activity
import android.content.Intent
import android.support.v4.app.FragmentActivity
import id.logtivity.codechallenge.MainApp
import id.logtivity.codechallenge.R
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton

inline fun <reified T : Activity> FragmentActivity.startActivity() {
    startActivity(Intent(this, T::class.java))
    this.overridePendingTransition(R.transition.fade_in, R.transition.fade_out)
    this.finish()
}

inline fun <reified T : Activity> FragmentActivity.startActivityWithValue(key: String, value: String) {
    startActivity(Intent(this, T::class.java).putExtra(key, value))
    this.overridePendingTransition(R.transition.fade_in, R.transition.fade_out)
    this.finish()
}

inline fun <reified T : Activity> FragmentActivity.startActivityWithFinish(tittle: String) {
    this.alert(tittle) {
        yesButton {
            MainApp.prefHelper.deleteSharedPreference()
            startActivity<T>()
            finish()
        }
        noButton {
            it.dismiss()
        }
        onCancelled {
            it.dismiss()
        }
    }.show()
}

