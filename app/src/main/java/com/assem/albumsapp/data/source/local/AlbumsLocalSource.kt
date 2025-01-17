package com.assem.albumsapp.data.source.local

import com.assem.albumsapp.data.models.dao.AlbumDaoModel
import com.assem.albumsapp.domain.entities.Album

/**
 * Created by mohamedassem
 * mohamed.assem.ali@gmail.com
 */

interface AlbumsLocalSource {
    suspend fun getAlbumsList(): List<AlbumDaoModel>
    suspend fun insertAlbums(albumsList: List<AlbumDaoModel>)
    suspend fun getAlbumById(albumId: String): AlbumDaoModel?
    suspend fun clearCache() : Boolean
}