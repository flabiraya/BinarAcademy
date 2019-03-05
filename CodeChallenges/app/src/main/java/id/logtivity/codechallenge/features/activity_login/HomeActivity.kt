package id.logtivity.codechallenge.features.activity_login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import id.logtivity.codechallenge.R
import id.logtivity.codechallenge.config.AndroidList
import id.logtivity.codechallenge.features.adapter.AdapterHome
import id.logtivity.codechallenge.preferences.alertHelper
import id.logtivity.codechallenge.preferences.getStringResources
import id.logtivity.codechallenge.preferences.myToast
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), HomeLayout {
    private lateinit var presenter: HomeView
    private var doubleBackPressed: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        presenter = HomeView(this, this)
        presenter.initData()
    }

    override fun getAllListAndroidData(data: List<AndroidList>?) {
        rviewHome_.layoutManager = LinearLayoutManager(this)
        data?.let {
            rviewHome_.adapter = AdapterHome(it) {
                alertHelper("You Picked ${it.codeName}")
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.navigation, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.menuLogout -> {
                presenter.userLogout()
                true
            }
            else -> super.onOptionsItemSelected(item)
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
