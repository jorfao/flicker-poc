package com.example.flickrpoc.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.flickrpoc.R

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    // private lateinit var viewModel by viewModels()

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
