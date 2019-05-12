package id.flabiraya.movieddbbinarcodechallange.screen.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import id.flabiraya.movieddbbinarcodechallange.base.BaseViewModel
import id.flabiraya.movieddbbinarcodechallange.data.model.Genre
import id.flabiraya.movieddbbinarcodechallange.data.model.Movie
import id.flabiraya.movieddbbinarcodechallange.data.repository.HomeRepository
import id.flabiraya.movieddbbinarcodechallange.data.source.remote.error.RetrofitException
import id.flabiraya.movieddbbinarcodechallange.utils.liveData.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel(val homeRepository: HomeRepository) : BaseViewModel() {

    val movies: MutableLiveData<List<Movie>> = MutableLiveData()

    val onMessageError = SingleLiveEvent<String>()

    val genres: MutableLiveData<List<Genre>> = MutableLiveData()



    fun getPopularMovies(page: Int = 1) {
        addDisposable(
            homeRepository.getPopularMovies(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response, error ->
                    response?.let {
                        when (it.isSuccessful) {
                            true -> movies.value = response.body()?.movies
                            false -> onMessageError.value = RetrofitException.toHttpError(response).getMessageError()
                        }
                    }
                    error?.let {
                        onMessageError.value = RetrofitException.toUnexpectedError(it).getMessageError()
                    }
                }
        )
    }

    fun getGenres() {
        addDisposable(
            homeRepository.getGenres()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response, error ->
                    response?.let {
                        when (it.isSuccessful) {
                            true -> genres.value = it.body()?.genres
                            false -> onMessageError.value = RetrofitException.toHttpError(response).getMessageError()
                        }
                    }
                    error?.let {
                        onMessageError.value = RetrofitException.toUnexpectedError(it).getMessageError()
                    }
                }
        )
    }


}
