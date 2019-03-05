package id.logtivity.feature

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.codeapp.R
import id.logtivity.preferences.AppDataPreferences
import kotlinx.android.synthetic.main.activity_login.*
import kotlin.math.sign

class LoginActivity : AppCompatActivity() {

    private lateinit var appData : AppDataPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupComponent()
        setupListener()



    }

    private var appData: AppDataPreferences

    private fun setupComponent() {
        appData = AppDataPreferences (this)
    }

    private fun setupListener() {
        bt_signin.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val username = et_login_email.text.toString().trim()
        val password = et_login_password.text.toString().trim()

        if (username == "admin" && password == "password") {
            appData.puIsUserLoggedIn(true)

        }
    }


}
