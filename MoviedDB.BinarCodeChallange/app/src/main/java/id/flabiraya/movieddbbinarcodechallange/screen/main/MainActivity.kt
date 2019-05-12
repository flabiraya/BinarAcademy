package id.flabiraya.movieddbbinarcodechallange.screen.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import id.flabiraya.movieddbbinarcodechallange.R
import id.flabiraya.movieddbbinarcodechallange.screen.home.HomeFragment
import id.flabiraya.movieddbbinarcodechallange.screen.home.HomeFragment.OnNavigationListener
import id.flabiraya.movieddbbinarcodechallange.utils.extention.addFragmentToActivity

class MainActivity : AppCompatActivity(), HomeFragment.OnNavigationListener {
    private val _mainFragment by lazy { MainFragment.newInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragmentToActivity(R.id.frame_layout_container, _mainFragment,false)
    }

    override fun navigateToFragment(fragment: Fragment) {
        addFragmentToActivity(R.id.frame_layout_container,fragment)
    }
}