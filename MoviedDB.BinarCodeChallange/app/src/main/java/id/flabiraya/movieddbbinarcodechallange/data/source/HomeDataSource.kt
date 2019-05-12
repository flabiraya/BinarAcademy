package id.flabiraya.movieddbbinarcodechallange.data.source

import id.flabiraya.movieddbbinarcodechallange.data.model.Movie
import id.flabiraya.movieddbbinarcodechallange.data.source.remote.response.GenresReponse
import id.flabiraya.movieddbbinarcodechallange.data.source.remote.response.PopularResponse
import io.reactivex.Single
import retrofit2.Response

interface HomeDatasource {
    interface Local

    interface Remote {
        fun getPopularMovies(page: Int): Single<Response<PopularResponse>>

        fun getGenres(): Single<Response<GenresReponse>>

        fun getMovieDetails(id: Int): Single<Response<Movie>>
    }
}
