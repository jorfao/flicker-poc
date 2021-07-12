package com.example.flickrpoc.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.flickrpoc.R
import com.example.flickrpoc.binding.FragmentDataBindingComponent
import com.example.flickrpoc.databinding.DetailsFragmentBinding
import com.example.flickrpoc.model.PhotoDetails
import com.example.flickrpoc.network.Resource
import com.example.flickrpoc.ui.BaseFragment
import com.example.flickrpoc.utils.Extensions.observe
import com.example.flickrpoc.utils.autoCleared

class DetailsFragment : BaseFragment<DetailsViewModel>() {
    override val viewModel: DetailsViewModel by viewModels { viewModelFactory }

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)

    var binding by autoCleared<DetailsFragmentBinding>()

    private val params by navArgs<DetailsFragmentArgs>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val dataBinding = DataBindingUtil.inflate<DetailsFragmentBinding>(
            inflater,
            R.layout.details_fragment,
            container,
            false,
            dataBindingComponent
        )
        binding = dataBinding
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.setPhotoId(params.photoId)

        observe(viewModel.details, ::setDetails)
    }

    private fun setDetails(details: Resource<PhotoDetails>) {
        if (details.data != null) {
            binding.photoDetails = viewModel.details
        }
    }
}
