package id.flabiraya.movieddbbinarcodechallange.utils.module

import id.flabiraya.movieddbbinarcodechallange.screen.home.HomeViewModel
import id.flabiraya.movieddbbinarcodechallange.screen.movie_detail_fragment.MovieDetailViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { MovieDetailViewModel(get()) }
}