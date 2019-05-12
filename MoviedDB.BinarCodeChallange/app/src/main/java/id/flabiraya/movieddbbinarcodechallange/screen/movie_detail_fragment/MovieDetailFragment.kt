package id.flabiraya.movieddbbinarcodechallange.screen.movie_detail_fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer

import id.flabiraya.movieddbbinarcodechallange.R
import id.flabiraya.movieddbbinarcodechallange.base.BaseFragment
import id.flabiraya.movieddbbinarcodechallange.databinding.MovieDetailFragmentBinding
import id.flabiraya.movieddbbinarcodechallange.screen.home.HomeViewModel
import id.flabiraya.movieddbbinarcodechallange.utils.extention.showToast
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailFragment : BaseFragment() {
    companion object {
        private const val ARG_ID = "ARG_ID"
        fun newInstance(id: Int): MovieDetailFragment = MovieDetailFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_ID, id)
            }
        }
    }

    private lateinit var _movieDetailFragmentBinding: MovieDetailFragmentBinding

    private val _homeViewModel: HomeViewModel by sharedViewModel()
    private val _movieDetailViewModel: MovieDetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _movieDetailFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.movie_detail_fragment, container, false)
        return _movieDetailFragmentBinding.root
    }

    override fun setUpView() {
    }

    override fun registerLiveData() {
        arguments?.let {
            _movieDetailViewModel.getMovieDetails(it.getInt(ARG_ID))
        }
        _movieDetailViewModel.onMessageError.observe(viewLifecycleOwner, Observer {
            it?.let {
                context?.showToast(it)
            }
        })
    }
}
