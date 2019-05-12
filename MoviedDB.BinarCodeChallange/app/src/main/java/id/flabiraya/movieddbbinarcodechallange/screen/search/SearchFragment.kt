package id.flabiraya.movieddbbinarcodechallange.screen.search

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import id.flabiraya.movieddbbinarcodechallange.R
import id.flabiraya.movieddbbinarcodechallange.base.BaseFragment

class SearchFragment : BaseFragment() {
    companion object {

        fun newInstance() = SearchFragment()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun setUpView() {
    }

    override fun bindView() {
    }
}

