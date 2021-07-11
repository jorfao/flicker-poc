package com.example.flickrpoc.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.flickrpoc.R
import com.example.flickrpoc.model.Tag
import com.example.flickrpoc.network.Resource
import com.example.flickrpoc.ui.BaseFragment
import com.example.flickrpoc.utils.Extensions.observe
import kotlinx.android.synthetic.main.main_fragment.text

class ListFragment : BaseFragment<ListViewModel>() {

    override val viewModel: ListViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initTagList(viewModel)
        // observe(viewModel.results, ::setTagText)
    }

    private fun setTagText(tags: Resource<List<Tag>>) {
        text.text = tags.data?.map { tag -> tag.name }?.joinToString { "," }
    }

    private fun initTagList(viewModel: ListViewModel) {
        viewModel.results.observe(
            viewLifecycleOwner,
            Observer { listResource ->
                // we don't need any null checks here for the adapter since LiveData guarantees that
                // it won't call us if fragment is stopped or not started.
                if (listResource?.data != null) {
                    text.text = listResource.data.map { tag -> tag.name }.joinToString ()
                } else {
                    text.text = "failed"
                }
            }
        )
    }
}
