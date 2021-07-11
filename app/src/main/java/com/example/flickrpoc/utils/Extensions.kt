package com.example.flickrpoc.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

object Extensions {
    fun <T : Any?> Fragment.observe(liveData: LiveData<T>, body: (T?) -> Unit) {
        liveData.observe(viewLifecycleOwner, Observer(body))
    }

    fun <T : Any?> Fragment.observe(liveData: LiveData<T>, observer: Observer<T>) {
        liveData.observe(viewLifecycleOwner, observer)
    }
}