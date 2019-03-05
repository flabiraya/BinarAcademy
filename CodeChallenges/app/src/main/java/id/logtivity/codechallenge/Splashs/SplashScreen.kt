package id.logtivity.codechallenge.Splashs

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import id.logtivity.codechallenge.R
import id.logtivity.codechallenge.config.configure.loginIntentKey
import id.logtivity.codechallenge.config.configure.loginIntentValues
import id.logtivity.codechallenge.config.configure.registerIntentKey
import id.logtivity.codechallenge.config.configure.registerIntentValues
import id.logtivity.codechallenge.features.activity_regist.RegistActivity
import id.logtivity.codechallenge.preferences.*
import kotlinx.android.synthetic.main.activity_regist.*
import kotlinx.android.synthetic.main.activity_splash_screen.*


class SplashScreen : AppCompatActivity(), SplashLayout {
    private lateinit var presenter: Splashview
    private var doubleBackPressed: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(id.logtivity.codechallenge.R.layout.activity_splash_screen)
        presenter = Splashview(this)
        presenter.getUserData()
    }

    override fun isRegistered(stat: Boolean) {
        initListener(stat)
    }

    fun initListener(stat: Boolean) {
        bt_Login.setOnClickListener {
            if (!stat) {
                alertHelper(getStringResources(id.logtivity.codechallenge.R.string.please_register))
            } else {
                startActivityWithValue<RegistActivity>(loginIntentKey, loginIntentValues)
            }
        }
        bt_register.setOnClickListener {
            startActivityWithValue<RegistActivity>(registerIntentKey, registerIntentValues)
        }
    }

    override fun onBackPressed() {
        if (doubleBackPressed) {
            super.onBackPressed()
            return
        }
        this.doubleBackPressed = true
        myToast(getStringResources(R.string.user_out))
        Handler().postDelayed(Runnable { doubleBackPressed = false }, 2000)
    }
}
