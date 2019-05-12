package id.flabiraya.movieddbbinarcodechallange.utils.module

import id.flabiraya.movieddbbinarcodechallange.data.repository.HomeRepository
import id.flabiraya.movieddbbinarcodechallange.data.source.HomeDatasource
import id.flabiraya.movieddbbinarcodechallange.data.source.remote.HomeRemoteDataSource
import id.flabiraya.movieddbbinarcodechallange.data.source.remote.api.AppApi
import org.koin.dsl.module.module

fun createHomeRemoteDataSource(appApi: AppApi): HomeDatasource.Remote = HomeRemoteDataSource(appApi)

fun createHomeRepository(homeRemoteDataSource: HomeDatasource.Remote): HomeRepository =
    HomeRepository(homeRemoteDataSource)

val repositoryModule = module {
    single { createHomeRepository(get()) }
    single { createHomeRemoteDataSource(get()) }
}