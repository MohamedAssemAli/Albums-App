package com.assem.albumsapp.data.source.remote.mapper

import com.assem.albumsapp.data.models.response.FeedResponse
import com.assem.albumsapp.domain.entities.Album
import javax.inject.Inject

/**
 * Created by mohamedassem
 * mohamed.assem.ali@gmail.com
 */

class RemoteFeedMapper @Inject constructor(private val remoteAlbumMapper: RemoteAlbumMapper) :
    BaseRemoteModelMapper<FeedResponse, List<Album>> {
    override fun convert(from: FeedResponse?): List<Album> {
        return from?.remoteFeed?.results.let {
            it?.map { album -> remoteAlbumMapper.convert(album) } ?: emptyList()
        }
    }
}