package id.flabiraya.movieddbbinarcodechallange.data.source.remote.api

import id.flabiraya.movieddbbinarcodechallange.data.model.Movie
import id.flabiraya.movieddbbinarcodechallange.data.source.remote.response.GenresReponse
import id.flabiraya.movieddbbinarcodechallange.data.source.remote.response.PopularResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AppApi {
    @GET("movie/popular")
    fun getPopularMovies(@Query("page") page: Int): Single<Response<PopularResponse>>

    @GET("movie/{id}")
    fun getMovieDetails(@Path("id") id: Int): Single<Response<Movie>>

    @GET("genre/movie/list")
    fun getGenres(): Single<Response<GenresReponse>>
}