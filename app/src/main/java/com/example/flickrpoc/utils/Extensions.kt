package com.example.flickrpoc.utils

import com.example.flickrpoc.model.Photo
import com.example.flickrpoc.network.PhotoDTO
import com.example.flickrpoc.utils.Values.IMAGE_URL

object Extensions {
    fun PhotoDTO.toPhoto() = Photo(this.id, this.owner, this.secret, this.server, this.farm, this.title, IMAGE_URL.format(this.farm, this.server, this.id, this.secret))
}
