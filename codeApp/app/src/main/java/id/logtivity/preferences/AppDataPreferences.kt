package id.logtivity.preferences

import android.content.Context
import android.content.SharedPreferences
import id.logtivity.R
import id.logtivity.config.configure

class AppDataPreferences (private val context: Context){

    companion object {
        private var preferences : SharedPreferences? = null

        fun getSharedPref(context: Context) : SharedPreferences = preferences ?: synchronized(this){
            getPreferences(context).also {
                preferences = it
            }
        }

        private fun getPreferences(context: Context) =
            context.getSharedPreferences("id.logtivity.app_data_preferences", Context.MODE_PRIVATE)
    }

    fun cleanUserData() {
        getSharedPref(context).edit().clear().apply()
    }
    fun putIsUserLoggedIn(isLoggedIn: Boolean) {
        with(getSharedPref(context).edit()) {
            putBoolean(configure.IS_USER_LOGGED_IN_PREF, isLoggedIn)
            apply()
        }
    }

    fun getIsUserLoggedIn(): Boolean {
        return getSharedPref(context).getBoolean(configure.IS_USER_LOGGED_IN_PREF, false)
    }

    fun putUsername(username: String) {
        with(getSharedPref(context).edit()) {
            putString(configure.USERNAME_KEY_PREF, username)
            apply()
        }
    }

    fun getUsername(): String {
        return getSharedPref(context).getString(configure.USERNAME_KEY_PREF, "") ?: ""
    }
}