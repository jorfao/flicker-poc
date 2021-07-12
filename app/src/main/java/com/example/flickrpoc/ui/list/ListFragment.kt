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
import com.example.flickrpoc.network.Status
import com.example.flickrpoc.ui.BaseFragment
import com.example.flickrpoc.utils.Extensions.observe
import com.example.flickrpoc.utils.autoCleared
import kotlinx.android.synthetic.main.main_fragment.list
import kotlinx.android.synthetic.main.main_fragment.swipe_refresh_layout
import timber.log.Timber

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
        adapter = MainListAdapter(
            dataBindingComponent, appExecutors,
            { tag ->
                // TODO implement get next page results
                Timber.d("Load more for $tag")
            },
            { photo ->
                Timber.d("Photo $photo.id clicked!")
                findNavController().navigate(
                    ListFragmentDirections.showDetails(photo.id)
                )
            }
        )

        list.adapter = adapter

        observe(viewModel.results, ::setTags)
        swipe_refresh_layout.setOnRefreshListener { viewModel.refreshTags() }
    }

    private fun setTags(resourceTags: Resource<List<Tag>>) {
        if (resourceTags.status == Status.LOADING) {
            swipe_refresh_layout.isRefreshing = true
            return
        }

        if (resourceTags.data != null) {

            resourceTags.data.forEach { tag ->
                observe(viewModel.getPhotosForTag(tag.name), ::setPhotos)
            }

            adapter.submitList(
                resourceTags.data.map { tag ->
                    TagItemWrapper(tagName = tag.name, mutableListOf())
                }
            )
        }
        swipe_refresh_layout.isRefreshing = false
    }

    private fun setPhotos(resourceTagPage: Resource<TagItemWrapper>) {
        if (resourceTagPage.status != Status.SUCCESS) {
            return
        }

        if (resourceTagPage.data != null) {
            val currentList = adapter.currentList.toMutableList()
            val tagPage = resourceTagPage.data
            adapter.submitList(null)
            adapter.submitList(currentList.also { it.find { tag -> tag.tagName == tagPage.tagName }?.apply { photos = tagPage.photos } })
        }
    }
}
