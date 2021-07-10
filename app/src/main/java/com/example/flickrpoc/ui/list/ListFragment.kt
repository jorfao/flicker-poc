package com.example.flickrpoc.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.flickrpoc.R
import com.example.flickrpoc.ui.BaseFragment

class ListFragment : BaseFragment() {
    companion object {
        fun newInstance() = ListFragment()
    }

    private val viewModel: @JvmSuppressWildcards ListViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // viewModel = by viewModels()(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }
}
