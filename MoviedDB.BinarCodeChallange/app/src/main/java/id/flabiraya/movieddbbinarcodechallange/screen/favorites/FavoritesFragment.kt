package id.flabiraya.movieddbbinarcodechallange.screen.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import id.flabiraya.movieddbbinarcodechallange.R
import id.flabiraya.movieddbbinarcodechallange.base.BaseFragment
import id.flabiraya.movieddbbinarcodechallange.databinding.FavoritesFragmentBinding

class FavoritesFragment : BaseFragment() {

    companion object {
        fun newInstance() = FavoritesFragment()
    }

    private lateinit var _favoritesFragmentBinding: FavoritesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _favoritesFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.favorites_fragment, container, false)
        return _favoritesFragmentBinding.root
    }

    override fun setUpView() {
    }

    override fun bindView() {
    }
}
