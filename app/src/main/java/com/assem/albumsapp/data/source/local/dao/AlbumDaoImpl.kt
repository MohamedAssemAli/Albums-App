package com.assem.albumsapp.data.source.local.dao

import android.util.Log
import com.assem.albumsapp.data.models.dao.AlbumDaoModel
import io.realm.kotlin.Realm
import javax.inject.Inject

/**
 * Created by mohamedassem
 * mohamed.assem.ali@gmail.com
 */

class AlbumDaoImpl @Inject constructor(private val realm: Realm) : AlbumDao {
    override suspend fun getAlbumsList(): List<AlbumDaoModel> {
        return realm.query(AlbumDaoModel::class).find()
    }

    override suspend fun insertAlbums(albumsList: List<AlbumDaoModel>) {
        albumsList.forEach {
            realm.write { copyToRealm(it) }
        }
    }

    override suspend fun getAlbumById(albumId: String): AlbumDaoModel? {
        return realm.query(AlbumDaoModel::class, "id == $0", albumId).first().find()
    }

    override suspend fun clearCache(): Boolean {
        return try {
            realm.write {
                deleteAll()
                true
            }
        } catch (e: Exception) {
            Log.d("AlbumDaoImpl", "clearCache: $e")
            false
        }
    }
}