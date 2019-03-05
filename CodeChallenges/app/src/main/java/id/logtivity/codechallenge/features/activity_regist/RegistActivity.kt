package id.logtivity.codechallenge.features.activity_regist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import id.logtivity.codechallenge.R
import id.logtivity.codechallenge.config.Account
import id.logtivity.codechallenge.Splashs.SplashScreen
import id.logtivity.codechallenge.preferences.*
import kotlinx.android.synthetic.main.activity_regist.*

class RegistActivity : AppCompatActivity(), RegistLayout {
    private lateinit var user: Account
    private lateinit var presenter: RegistView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regist)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter = RegistView(this, this)
        presenter.getIntent(intent)
        presenter.onCreate()
        user = Account()
    }

    fun register() {
        bt_register.setOnClickListener {
            if (et_full_name.text.isNullOrEmpty()) {
                et_full_name.requestError(getStringResources(R.string.name_checker))
            } else if (et_email_address.text.isNullOrEmpty()) {
                et_email_address.requestError(getStringResources(R.string.email_checker))
            } else if (et_organization.text.isNullOrEmpty()) {
                et_organization.requestError(getStringResources(R.string.organization_checker))
            } else if (et_passwords.text.isNullOrEmpty()) {
                et_passwords.requestError(getStringResources(R.string.password_checker))
            } else if (et_confirm_password.text.isNullOrEmpty()) {
                et_confirm_password.requestError(getStringResources(R.string.password_checker))
            } else {
                user.name = et_full_name.text.toString().trim()
                user.email = et_email_address.text.toString().trim()
                user.organization = et_organization.text.toString().trim()
                if (et_passwords.text.toString().trim().equals(
                        et_confirm_password.text.toString().trim(),
                        ignoreCase = true
                    )
                ) {
                    user.password = et_passwords.text.toString().trim()
                    presenter.register(user)
                    clearEditText()
                } else {
                    this.alertHelper(getStringResources(R.string.password_not_same))
                }
            }
        }
    }

    fun login() {
        et_full_name.gone()
        et_organization.gone()
        et_confirm_password.gone()
        bt_register.text = resources.getString(R.string.login)
        setTitle(getStringResources(R.string.login))
        bt_register.setOnClickListener {
            if (et_email_address.text.isNullOrEmpty()) {
                et_email_address.requestError(getStringResources(R.string.email_checker))
            } else if (et_passwords.text.isNullOrEmpty()) {
                et_passwords.requestError(getStringResources(R.string.password_checker))
            } else {
                presenter.login(et_email_address.text.toString().trim(), et_passwords.text.toString().trim())
            }
        }
    }

    override fun isLogin(status: Boolean) {
        if (status) {
            register()
        } else {
            login()
        }
    }


    fun clearEditText() {
        et_full_name.clear()
        et_email_address.clear()
        et_organization.clear()
        et_passwords.clear()
        et_confirm_password.clear()

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                startActivity<SplashScreen>()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
