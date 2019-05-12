package id.flabiraya.movieddbbinarcodechallange.screen.home

import androidx.lifecycle.Observer
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager

import id.flabiraya.movieddbbinarcodechallange.R
import id.flabiraya.movieddbbinarcodechallange.base.BaseFragment
import id.flabiraya.movieddbbinarcodechallange.databinding.HomeFragmentBinding
import id.flabiraya.movieddbbinarcodechallange.screen.movie_detail_fragment.MovieDetailFragment
import id.flabiraya.movieddbbinarcodechallange.utils.extention.showToast
import id.flabiraya.movieddbbinarcodechallange.utils.liveData.autoCleared
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : BaseFragment() {
    companion object {

        fun newInstance() = HomeFragment()
    }

    private lateinit var _homeFragmentBinding: HomeFragmentBinding

    private val _homeViewModel: HomeViewModel by sharedViewModel()

    private var _onNavigationListener: OnNavigationListener? = null

    private var _homeAdapter by autoCleared<HomeAdapter>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnNavigationListener) {
            _onNavigationListener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _homeFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)
        return _homeFragmentBinding.root
    }

    override fun setUpView() {
        _homeAdapter = HomeAdapter {
            _onNavigationListener?.navigateToFragment(MovieDetailFragment.newInstance(it.id))
        }
        _homeFragmentBinding.run {
            recyclerViewHome.run {
                adapter = _homeAdapter
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        _onNavigationListener = null
    }

    override fun registerLiveData() {
        _homeViewModel.getPopularMovies()
        _homeViewModel.getGenres()
        _homeViewModel.movies.observe(viewLifecycleOwner, Observer {
            _homeAdapter.addData(it)
        })
        _homeViewModel.genres.observe(viewLifecycleOwner, Observer {
            _homeAdapter.addGenres(it)
        })
        _homeViewModel.onMessageError.observe(viewLifecycleOwner, Observer {
            it?.let {
                context?.showToast(it)
            }
        })
    }

    interface OnNavigationListener {
        fun navigateToFragment(fragment: Fragment)
    }
}