package com.example.flickrpoc.ui.details

import androidx.fragment.app.viewModels
import com.example.flickrpoc.ui.BaseFragment

class DetailsFragment : BaseFragment<DetailsViewModel>() {
    override val viewModel: DetailsViewModel by viewModels { viewModelFactory }
}
