package id.logtivity.feature

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.codeapp.R
import id.logtivity.preferences.AppDataPreferences

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        class MainActivity : AppCompatActivity() {

            private lateinit var appData: AppDataPreferences

            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_home)

                setupComponent()
                setupListener()
            }

            override fun onResume() {
                super.onResume()
                tv_main_username.text = appData.getUsername()
            }

            private fun setupComponent() {
                appData = AppDataPreferences(this)
            }

            private fun setupListener() {
                btn_main_edit_profile.setOnClickListener {
                    startActivity(Intent(this@MainActivity, ::class.java))
                }

                bt_logout.setOnClickListener {
                    appData.cleanUserData()
                    startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                    finish()
                }
            }
        }

    }
}