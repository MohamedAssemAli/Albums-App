package com.assem.albumsapp.data.models.response


import com.assem.albumsapp.data.models.remote.RemoteFeed
import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class FeedResponse(
    @SerializedName("feed")
    @Expose
    var remoteFeed: RemoteFeed?
)