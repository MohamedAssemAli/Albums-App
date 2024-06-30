package com.assem.albumsapp.domain.repository

import com.assem.albumsapp.domain.entities.Album
import com.assem.albumsapp.data.utils.ResourceState
import kotlinx.coroutines.flow.Flow


interface AlbumsRepository {
    suspend fun getAlbumsFeed(fetchFromRemote: Boolean): Flow<ResourceState<List<Album>>>
    suspend fun getAlbumById(albumId: String): Flow<ResourceState<Album>>
}
