package com.assem.albumsapp.data.models.remote


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class RemoteFeed(
    @SerializedName("author")
    @Expose
    var remoteAuthor: RemoteAuthor?,
    @SerializedName("copyright")
    @Expose
    var copyright: String?,
    @SerializedName("country")
    @Expose
    var country: String?,
    @SerializedName("icon")
    @Expose
    var icon: String?,
    @SerializedName("id")
    @Expose
    var id: String?,
    @SerializedName("links")
    @Expose
    var remoteLinks: List<RemoteLink?>?,
    @SerializedName("results")
    @Expose
    var results: List<RemoteAlbum?>?,
    @SerializedName("title")
    @Expose
    var title: String?,
    @SerializedName("updated")
    @Expose
    var updated: String?
)