package com.assem.albumsapp.data.source.local

import com.assem.albumsapp.domain.entities.Album

/**
 * Created by mohamedassem
 * mohamed.assem.ali@gmail.com
 */

interface AlbumsLocalSource {
    suspend fun getAlbumsFeed(): List<Album>
    suspend fun insertAlbumsFeed(albumsList: List<Album>)
    suspend fun clearCache()
}