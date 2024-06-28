package com.assem.albumsapp.data.models.remote


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class RemoteAuthor(
    @SerializedName("name")
    @Expose
    var name: String?,
    @SerializedName("url")
    @Expose
    var url: String?
)