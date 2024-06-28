package com.assem.albumsapp.domain.repository

import com.assem.albumsapp.domain.entities.Album
import com.assem.albumsapp.util.Resource
import kotlinx.coroutines.flow.Flow


interface AlbumsRepository {
    suspend fun getAlbumsFeed(fetchFromRemote: Boolean): Flow<Resource<List<Album>>>
}
