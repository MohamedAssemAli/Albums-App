package com.assem.albumsapp.data.models.remote


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class RemoteAlbum(
    @SerializedName("artistId")
    @Expose
    var artistId: String?,
    @SerializedName("artistName")
    @Expose
    var artistName: String?,
    @SerializedName("artistUrl")
    @Expose
    var artistUrl: String?,
    @SerializedName("artworkUrl100")
    @Expose
    var artworkUrl100: String?,
    @SerializedName("contentAdvisoryRating")
    @Expose
    var contentAdvisoryRating: String?,
    @SerializedName("genres")
    @Expose
    var remoteGenres: List<RemoteGenre?>?,
    @SerializedName("id")
    @Expose
    var id: String?,
    @SerializedName("kind")
    @Expose
    var kind: String?,
    @SerializedName("name")
    @Expose
    var name: String?,
    @SerializedName("releaseDate")
    @Expose
    var releaseDate: String?,
    @SerializedName("url")
    @Expose
    var url: String?
)