package com.assem.albumsapp.data.source.local

import com.assem.albumsapp.data.models.dao.AlbumDaoModel
import com.assem.albumsapp.data.source.local.dao.AlbumDao
import com.assem.albumsapp.domain.entities.Album
import javax.inject.Inject

/**
 * Created by mohamedassem
 * mohamed.assem.ali@gmail.com
 */

class AlbumsLocalSourceImpl @Inject constructor(private val albumDao: AlbumDao) :
    AlbumsLocalSource {
    override suspend fun getAlbumsList(): List<AlbumDaoModel> {
        return albumDao.getAlbumsList()
    }

    override suspend fun insertAlbums(albumsList: List<AlbumDaoModel>) {
        return albumDao.insertAlbums(albumsList)
    }

    override suspend fun getAlbumById(albumId: String): AlbumDaoModel? {
        return albumDao.getAlbumById(albumId)
    }

    override suspend fun clearCache(): Boolean {
        return albumDao.clearCache()
    }
}