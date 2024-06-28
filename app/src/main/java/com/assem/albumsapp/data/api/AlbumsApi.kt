package com.assem.albumsapp.data.api

import com.assem.albumsapp.data.models.response.FeedResponse
import com.assem.albumsapp.data.source.remote.RemoteConfig
import retrofit2.http.GET

interface AlbumsApi {
    @GET(RemoteConfig.ALBUMS_URL)
    suspend fun getAlbumsFeed(): FeedResponse
}