package id.flabiraya.movieddbbinarcodechallange.data.source.remote

import id.flabiraya.movieddbbinarcodechallange.data.model.Movie
import id.flabiraya.movieddbbinarcodechallange.data.source.HomeDatasource
import id.flabiraya.movieddbbinarcodechallange.data.source.remote.api.AppApi
import id.flabiraya.movieddbbinarcodechallange.data.source.remote.response.GenresReponse
import id.flabiraya.movieddbbinarcodechallange.data.source.remote.response.PopularResponse
import io.reactivex.Single
import retrofit2.Response

class HomeRemoteDataSource(val appApi: AppApi) : HomeDatasource.Remote {
    override fun getMovieDetails(id: Int): Single<Response<Movie>> = appApi.getMovieDetails(id)


    override fun getGenres(): Single<Response<GenresReponse>> = appApi.getGenres()

    override fun getPopularMovies(page: Int): Single<Response<PopularResponse>> = appApi.getPopularMovies(page)
}
