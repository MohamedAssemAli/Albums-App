package com.assem.albumsapp.data.source.local.dao

import com.assem.albumsapp.data.models.dao.AlbumDaoModel

/**
 * Created by mohamedassem
 * mohamed.assem.ali@gmail.com
 */

interface AlbumDao {
    suspend fun getAlbumsList(): List<AlbumDaoModel>
    suspend fun insertAlbums(albumsList: List<AlbumDaoModel>)
    suspend fun getAlbumById(albumId: String): AlbumDaoModel?
    suspend fun clearCache(): Boolean
}