package id.logtivity.codechallenge.features.activity_regist

import android.content.Intent
import android.support.v4.app.FragmentActivity
import com.google.gson.Gson
import id.logtivity.codechallenge.MainApp.Companion.prefHelper
import id.logtivity.codechallenge.R
import id.logtivity.codechallenge.config.Account
import id.logtivity.codechallenge.config.configure.registerIntentKey
import id.logtivity.codechallenge.config.configure.registerIntentValues
import id.logtivity.codechallenge.config.configure.saveUserkey
import id.logtivity.codechallenge.features.activity_login.HomeActivity
import id.logtivity.codechallenge.preferences.alertHelper
import id.logtivity.codechallenge.preferences.getStringResources
import id.logtivity.codechallenge.preferences.startActivity

class RegistView(var act: FragmentActivity, var mView: RegistActivity) {
    private val gson: Gson = Gson()
    private var user: Account = Account()

    fun onCreate() {
        val tmpData = prefHelper.getStringInSharedPreference(saveUserkey)
        if (!tmpData.isNullOrEmpty()) {
            user = gson.fromJson(tmpData, Account::class.java)
        }
    }

    fun getIntent(intent: Intent) {
        if (intent.extras.getString(registerIntentKey).equals(registerIntentValues, ignoreCase = true)) {
            mView.isLogin(true)
        } else {
            mView.isLogin(false)

        }
    }

    fun register(data: Account?) {
        if (data != null) {
            prefHelper.saveStringInSharedPreference(saveUserkey, gson.toJson(data))
            act.startActivity<HomeActivity>()
        }
    }

    fun login(email: String, password: String) {
        if (email.equals(user.email) && password.equals(user.password)) {
            act.startActivity<HomeActivity>()
        } else {
            act.alertHelper(act.getStringResources(R.string.password_wrong))
        }
    }
}