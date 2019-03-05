package id.logtivity.feature

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.codeapp.WelcomeActivity
import com.example.codeapp.R
import id.logtivity.preferences.AppDataPreferences

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)



        val appData = AppDataPreferences(this)
        val isUserLoggedIn = appData.getIsUserLoggedIn()
        if (isUserLoggedIn) {
            routeTo(MainActivity::class.java)
        } else {
            routeTo(LoginActivity::class.java)
        }
    }

    private fun routeTo(cls: Class<*>) {
        val intent = Intent(this@SplashActivity, cls)
        startActivity(intent)
        finish()
    }
}