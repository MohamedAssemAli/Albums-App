package com.assem.albumsapp.data.source.local

import com.assem.albumsapp.domain.entities.Album
import javax.inject.Inject

/**
 * Created by mohamedassem
 * mohamed.assem.ali@gmail.com
 */

class AlbumsLocalSourceImpl @Inject constructor() :
    AlbumsLocalSource {
    override suspend fun getAlbumsFeed(): List<Album> {
        return emptyList()
//        return listOf(
//            Album(name = "AAA", artistName = "AAA"),
//            Album(name = "BBB", artistName = "BBB"),
//            Album(name = "CCC", artistName = "CCC"),
//            Album(name = "DDD", artistName = "DDD")
//        )
    }

    override suspend fun insertAlbumsFeed(albumsList: List<Album>) {}

    override suspend fun clearCache() {}
}