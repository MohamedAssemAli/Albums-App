package com.assem.albumsapp.data.source.remote

import com.assem.albumsapp.data.api.AlbumsApi
import com.assem.albumsapp.data.models.response.FeedResponse
import javax.inject.Inject

class AlbumsRemoteSourceImpl @Inject constructor(private val albumsApi: AlbumsApi) :
    AlbumsRemoteSource {
    override suspend fun getAlbumsFeed(): FeedResponse =
        albumsApi.getAlbumsFeed()
}
