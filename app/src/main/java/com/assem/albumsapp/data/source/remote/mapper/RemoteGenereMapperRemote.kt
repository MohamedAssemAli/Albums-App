package com.assem.albumsapp.data.source.remote.mapper

import com.assem.albumsapp.data.models.remote.RemoteGenre
import com.assem.albumsapp.domain.entities.Genre
import javax.inject.Inject

/**
 * Created by mohamedassem
 * mohamed.assem.ali@gmail.com
 */
class RemoteGenereMapperRemote @Inject constructor() : BaseRemoteModelMapper<RemoteGenre, Genre> {
    override fun convert(from: RemoteGenre?): Genre {
        return from?.let {
            Genre(
                genreId = it.genreId.orEmpty(),
                name = it.name.orEmpty(),
                url = it.url.orEmpty()
            )
        } ?: Genre()
    }
}