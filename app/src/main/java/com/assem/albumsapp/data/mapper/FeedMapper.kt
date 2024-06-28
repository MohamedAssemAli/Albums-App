package com.assem.albumsapp.data.mapper

import com.assem.albumsapp.data.models.response.FeedResponse
import com.assem.albumsapp.domain.entities.Album
import javax.inject.Inject

/**
 * Created by mohamedassem
 * mohamed.assem.ali@gmail.com
 */

class FeedMapper @Inject constructor(private val albumMapper: AlbumMapper) :
    BaseModelMapper<FeedResponse, List<Album>> {
    override fun convert(from: FeedResponse?): List<Album> {
        return from?.remoteFeed?.results.let {
            it?.map { album -> albumMapper.convert(album) } ?: emptyList()
        }
    }
}