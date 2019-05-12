package id.flabiraya.movieddbbinarcodechallange.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        bindView()
    }

    protected abstract fun setUpView()

    open protected fun bindView() {
        registerLiveData()
    }

    open protected fun registerLiveData() {
    }
}