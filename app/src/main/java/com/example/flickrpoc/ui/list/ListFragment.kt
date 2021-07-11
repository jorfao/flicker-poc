package com.example.flickrpoc.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.flickrpoc.R
import com.example.flickrpoc.binding.FragmentDataBindingComponent
import com.example.flickrpoc.model.Tag
import com.example.flickrpoc.network.Resource
import com.example.flickrpoc.ui.BaseFragment
import com.example.flickrpoc.utils.Extensions.observe
import com.example.flickrpoc.utils.autoCleared
import kotlinx.android.synthetic.main.main_fragment.list
import kotlinx.android.synthetic.main.main_fragment.swipe_refresh_layout

class ListFragment : BaseFragment<ListViewModel>() {

    override val viewModel: ListViewModel by viewModels { viewModelFactory }

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)

    var adapter by autoCleared<MainListAdapter>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = MainListAdapter(dataBindingComponent, appExecutors){ photo ->
            //findNavController().navigate(
                //ListFragmentDirections.showDetails(photo.id)
            //)
        }

        list.adapter = adapter

        observe(viewModel.results, ::setTagText)
        swipe_refresh_layout.setOnRefreshListener { viewModel.refreshTags() }
    }

    private fun setTagText(resourceTags: Resource<List<Tag>>) {
        if (resourceTags is Resource.Loading) {
            swipe_refresh_layout.isRefreshing = true
            return
        }

        if (resourceTags.data != null) {
            adapter.submitList(resourceTags.data)

            //text.text = resourceTags.data.map { tag -> tag.name }.joinToString()
        } else {
            //text.text = "failed"
        }
        swipe_refresh_layout.isRefreshing = false
    }
}
