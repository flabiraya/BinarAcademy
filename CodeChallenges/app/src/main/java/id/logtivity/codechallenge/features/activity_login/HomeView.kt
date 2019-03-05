package id.logtivity.codechallenge.features.activity_login

import android.support.v4.app.FragmentActivity
import id.logtivity.codechallenge.R
import id.logtivity.codechallenge.Splashs.SplashScreen
import id.logtivity.codechallenge.config.AndroidList
import id.logtivity.codechallenge.preferences.getStringResources
import id.logtivity.codechallenge.preferences.startActivityWithFinish

class HomeView (var mView: HomeLayout, var act: FragmentActivity) {
        private var allData: MutableList<AndroidList> = mutableListOf()

        fun initData() {
            allData.run {
                add(AndroidList("Alpha", "Android 1.0", "Api level 1"))
                add(AndroidList("Beta", "Android 1.1", "Api level 2"))
                add(AndroidList("Cupcake", "Android 1.5", "Api level 3"))
                add(AndroidList("Donut", "Android 1.6", "Api level 4"))
                add(AndroidList("Eclair", "Android 2.0", "Api level 5"))
                add(AndroidList("Froyo", "Android 2.2", "Api level 8"))
                add(AndroidList("Gingerbread", "Android 2.3", "Api level 9"))
                add(AndroidList("Honeycomb", "Android 3.0", "Api level 11"))
                add(AndroidList("Ice Cream Sandwich", "Android 4.0", "Api level 14"))
                add(AndroidList("Jelly Bean", "Android 4.1", "Api level 16"))
                add(AndroidList("KitKat", "Android 4.4", "Api level 19"))
                add(AndroidList("Lollipop", "Android 5.1", "Api level 21"))
                add(AndroidList("Marshmallow", "Android 6.0", "Api level 23"))
                add(AndroidList("Nougat", "Android 7.0", "Api level 24"))
                add(AndroidList("Oreo", "Android 8.0", "Api level 27"))
                add(AndroidList("Pie", "Android 9.0", "Api level 28"))
            }
            mView.getAllListAndroidData(allData)
}

        fun userLogout() {
            act.startActivityWithFinish<SplashScreen>(act.getStringResources(R.string.logout_user))
        }
    }
