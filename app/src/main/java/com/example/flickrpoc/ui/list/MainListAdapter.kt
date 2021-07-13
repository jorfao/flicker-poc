package com.example.flickrpoc.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.example.flickrpoc.R
import com.example.flickrpoc.databinding.TagItemBinding
import com.example.flickrpoc.model.Photo
import com.example.flickrpoc.ui.common.DataBoundListAdapter
import com.example.flickrpoc.utils.AppExecutors

class MainListAdapter(
    private val dataBindingComponent: DataBindingComponent,
    private val appExecutors: AppExecutors,
    private val onScrollEndCallback: ((String) -> Unit)?,
    private val photoClickCallback: ((Photo) -> Unit)?,
) : DataBoundListAdapter<TagItemWrapper, TagItemBinding>(
    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<TagItemWrapper>() {
        override fun areItemsTheSame(oldItem: TagItemWrapper, newItem: TagItemWrapper): Boolean {
            return oldItem.tagName == newItem.tagName
        }

        override fun areContentsTheSame(oldItem: TagItemWrapper, newItem: TagItemWrapper): Boolean {
            return oldItem.photos == newItem.photos
        }
    }
) {

    override fun createBinding(parent: ViewGroup): TagItemBinding =
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.tag_item,
            parent,
            false,
            dataBindingComponent
        )

    override fun bind(binding: TagItemBinding, item: TagItemWrapper) {
        binding.tagItemWrapper = item

        val innerAdapter = PhotoListAdapter(dataBindingComponent, appExecutors, photoClickCallback)
        innerAdapter.submitList(item.photos)

        binding.photoList.adapter = innerAdapter

        /* TODO Add paging
        binding.photoList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastPosition = layoutManager.findLastVisibleItemPosition()
                if (lastPosition == innerAdapter.itemCount - 1) {
                    onScrollEndCallback?.invoke(item.tagName)
                }
            }
        })
         */
    }
}
