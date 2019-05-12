package id.flabiraya.movieddbbinarcodechallange

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.flabiraya.movieddbbinarcodechallange.utils.module.networkModule
import id.flabiraya.movieddbbinarcodechallange.utils.module.repositoryModule
import id.flabiraya.movieddbbinarcodechallange.utils.module.viewModelModule
import org.koin.android.ext.android.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(
            this,
            arrayListOf(
                viewModelModule,
                networkModule,
                repositoryModule
            )
        )
    }
}