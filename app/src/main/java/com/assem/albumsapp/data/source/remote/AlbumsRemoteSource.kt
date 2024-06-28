package com.assem.albumsapp.data.source.remote

import com.assem.albumsapp.data.models.response.FeedResponse

interface AlbumsRemoteSource {
    suspend fun getAlbumsFeed(): FeedResponse
}
