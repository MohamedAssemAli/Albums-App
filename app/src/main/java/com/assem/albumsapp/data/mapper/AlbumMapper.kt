package com.assem.albumsapp.data.mapper

import com.assem.albumsapp.data.models.remote.RemoteAlbum
import com.assem.albumsapp.domain.entities.Album
import javax.inject.Inject

/**
 * Created by mohamedassem
 * mohamed.assem.ali@gmail.com
 */

class AlbumMapper @Inject constructor(private val genereMapper: GenereMapper) :
    BaseModelMapper<RemoteAlbum, Album> {
    override fun convert(from: RemoteAlbum?): Album {
        return from?.let {
            Album(
                artistId = it.artistId.orEmpty(),
                artistName = it.artistName.orEmpty(),
                artistUrl = it.artistUrl.orEmpty(),
                artworkUrl100 = it.artworkUrl100.orEmpty(),
                contentAdvisoryRating = it.contentAdvisoryRating.orEmpty(),
                genres =
                it.remoteGenres?.map { genre -> genereMapper.convert(genre) }
                    ?: emptyList(),
                id = it.id.orEmpty(),
                kind = it.kind.orEmpty(),
                name = it.name.orEmpty(),
                releaseDate = it.releaseDate.orEmpty(),
                url = it.url.orEmpty(),
            )
        } ?: Album()

    }
}