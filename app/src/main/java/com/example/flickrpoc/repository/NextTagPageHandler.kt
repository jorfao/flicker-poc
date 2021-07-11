package com.example.flickrpoc.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.flickrpoc.network.Resource
import com.example.flickrpoc.network.Status
import com.example.flickrpoc.ui.list.LoadMoreState

class NextTagPageHandler(private val photosRepository: PhotosRepository) : Observer<Resource<Boolean>> {
    private var nextPageLiveData: LiveData<Resource<Boolean>>? = null
    val loadMoreState = MutableLiveData<LoadMoreState>()

    private var query: String? = null
    private var _hasMore: Boolean = false
    val hasMore
        get() = _hasMore

    init {
        reset()
    }

    fun queryNextPage(query: String) {
        if (this.query == query) {
            return
        }
        unregister()
        this.query = query
        nextPageLiveData = photosRepository.getNextTagPage(query)
        loadMoreState.value = LoadMoreState(
            isRunning = true,
            errorMessage = null
        )
        nextPageLiveData?.observeForever(this)
    }

    override fun onChanged(result: Resource<Boolean>?) {
        if (result == null) {
            reset()
        } else {
            when (result.status) {
                Status.SUCCESS -> {
                    _hasMore = result.data == true
                    unregister()
                    loadMoreState.setValue(
                        LoadMoreState(
                            isRunning = false,
                            errorMessage = null
                        )
                    )
                }
                Status.ERROR -> {
                    _hasMore = true
                    unregister()
                    loadMoreState.setValue(
                        LoadMoreState(
                            isRunning = false,
                            errorMessage = result.message
                        )
                    )
                }
                Status.LOADING -> {
                    // ignore
                }
            }
        }
    }

    private fun unregister() {
        nextPageLiveData?.removeObserver(this)
        nextPageLiveData = null
        if (_hasMore) {
            query = null
        }
    }

    fun reset() {
        unregister()
        _hasMore = true
        loadMoreState.value = LoadMoreState(
            isRunning = false,
            errorMessage = null
        )
    }
}
