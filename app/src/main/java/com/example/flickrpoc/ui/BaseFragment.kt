package com.example.flickrpoc.ui

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.flickrpoc.di.Injectable
import com.example.flickrpoc.utils.AppExecutors
import javax.inject.Inject

abstract class BaseFragment<V : ViewModel> : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    protected abstract val viewModel: V
}
