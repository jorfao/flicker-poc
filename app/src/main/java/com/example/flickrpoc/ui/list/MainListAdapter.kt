package com.example.flickrpoc.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.example.flickrpoc.R
import com.example.flickrpoc.databinding.TagItemBinding
import com.example.flickrpoc.model.Photo
import com.example.flickrpoc.model.Tag
import com.example.flickrpoc.ui.common.DataBoundListAdapter
import com.example.flickrpoc.utils.AppExecutors

class MainListAdapter(
    private val dataBindingComponent: DataBindingComponent,
    appExecutors: AppExecutors,
    private val photoClickCallback: ((Photo) -> Unit)?
) : DataBoundListAdapter<Tag, TagItemBinding>(
    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<Tag>() {
        override fun areItemsTheSame(oldItem: Tag, newItem: Tag): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Tag, newItem: Tag): Boolean {
            // TODO compare photos
            return oldItem.name == newItem.name
        }
    }
) {

    override fun createBinding(parent: ViewGroup): TagItemBinding {
        val binding = DataBindingUtil.inflate<TagItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.tag_item,
            parent,
            false,
            dataBindingComponent
        )
        /*
        binding.root.setOnClickListener {
            binding.tag?.let {
                repoClickCallback?.invoke(it)
            }
        }*/
        return binding
    }

    override fun bind(binding: TagItemBinding, item: Tag) {
        binding.tag = item
    }
}
