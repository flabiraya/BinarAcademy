package id.logtivity.codechallenge.Splashs

import id.logtivity.codechallenge.MainApp
import id.logtivity.codechallenge.config.configure

class Splashview (var mView: SplashLayout) {
    fun getUserData() {
        val tmpData = MainApp.prefHelper.getStringInSharedPreference(configure.saveUserkey)
        if (tmpData.equals("")) {
            mView.isRegistered(false)
        } else {
            mView.isRegistered(true)
        }
    }
}