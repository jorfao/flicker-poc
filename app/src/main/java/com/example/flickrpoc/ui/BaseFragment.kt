package com.example.flickrpoc.ui

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.flickrpoc.utils.AppExecutors
import com.example.flickrpoc.di.Injectable
import javax.inject.Inject

open class BaseFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors
}
