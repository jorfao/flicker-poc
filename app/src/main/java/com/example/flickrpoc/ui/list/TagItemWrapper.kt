package com.example.flickrpoc.ui.list

import com.example.flickrpoc.model.Photo

data class TagItemWrapper(val tagName: String, val photos: MutableList<Photo>)
