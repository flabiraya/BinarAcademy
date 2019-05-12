package id.flabiraya.movieddbbinarcodechallange.data.repository

import id.flabiraya.movieddbbinarcodechallange.data.model.Movie
import id.flabiraya.movieddbbinarcodechallange.data.source.HomeDatasource
import id.flabiraya.movieddbbinarcodechallange.data.source.remote.response.GenresReponse
import id.flabiraya.movieddbbinarcodechallange.data.source.remote.response.PopularResponse
import io.reactivex.Single
import retrofit2.Response

class HomeRepository(
    val homeRemoteDataSource: HomeDatasource.Remote
) : HomeDatasource.Remote, HomeDatasource.Local {

    override fun getMovieDetails(id: Int): Single<Response<Movie>> = homeRemoteDataSource.getMovieDetails(id)

    override fun getGenres(): Single<Response<GenresReponse>> = homeRemoteDataSource.getGenres()

    override fun getPopularMovies(page: Int): Single<Response<PopularResponse>> =
        homeRemoteDataSource.getPopularMovies(page)
}