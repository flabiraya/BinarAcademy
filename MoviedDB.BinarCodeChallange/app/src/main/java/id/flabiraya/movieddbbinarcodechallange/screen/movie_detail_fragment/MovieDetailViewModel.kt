package id.flabiraya.movieddbbinarcodechallange.screen.movie_detail_fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import id.flabiraya.movieddbbinarcodechallange.base.BaseViewModel
import id.flabiraya.movieddbbinarcodechallange.data.model.Movie
import id.flabiraya.movieddbbinarcodechallange.data.repository.HomeRepository
import id.flabiraya.movieddbbinarcodechallange.data.source.remote.error.RetrofitException
import id.flabiraya.movieddbbinarcodechallange.utils.liveData.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieDetailViewModel(val homeRepository: HomeRepository) : BaseViewModel() {
    val movie: MutableLiveData<Movie> = MutableLiveData()
    val onMessageError = SingleLiveEvent<String>()

    fun getMovieDetails(id: Int) {
        addDisposable(
            homeRepository.getMovieDetails(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response, error ->
                    response?.let {
                        when (it.isSuccessful) {
                            true -> movie.value = it.body()
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

