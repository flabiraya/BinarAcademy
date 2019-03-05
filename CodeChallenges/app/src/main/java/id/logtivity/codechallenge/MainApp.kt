package id.logtivity.codechallenge

import android.app.Application
import id.logtivity.codechallenge.preferences.DataPreferences

class MainApp : Application() {
    companion object {
        lateinit var prefHelper: DataPreferences
    }

    override fun onCreate() {
        super.onCreate()
        prefHelper = DataPreferences(this)
    }
}