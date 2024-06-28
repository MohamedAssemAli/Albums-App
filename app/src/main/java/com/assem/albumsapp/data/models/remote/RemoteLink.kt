package com.assem.albumsapp.data.models.remote


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class RemoteLink(
    @SerializedName("self")
    @Expose
    var self: String?
)