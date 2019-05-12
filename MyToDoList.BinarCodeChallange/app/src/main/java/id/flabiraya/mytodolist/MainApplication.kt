package id.flabiraya.mytodolist

import android.app.Application
import com.google.firebase.database.FirebaseDatabase

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        //Enable Firebase persistence for offline access
        FirebaseDatabase.getInstance().setPersistenceEnabled(false)
    }
}