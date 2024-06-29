package com.assem.albumsapp.data.source.local.mapper

import com.assem.albumsapp.data.models.dao.AlbumDaoModel
import com.assem.albumsapp.data.models.dao.GenreDaoModel
import com.assem.albumsapp.domain.entities.Album
import com.assem.albumsapp.domain.entities.Genre
import io.realm.kotlin.ext.toRealmList

/**
 * Created by mohamedassem
 * mohamed.assem.ali@gmail.com
 */

fun Album.toAlbumDaoModel(): AlbumDaoModel {
    return AlbumDaoModel(
        artistId = artistId,
        artistName = artistName,
        artistUrl = artistUrl,
        artworkUrl100 = artworkUrl100,
        contentAdvisoryRating = contentAdvisoryRating,
        genres = genres.map { it.toGenreDaoModel() }.toRealmList(),
        id = id,
        kind = kind,
        name = name,
        releaseDate = releaseDate,
        url = url,
    )
}

fun Genre.toGenreDaoModel(): GenreDaoModel {
    return GenreDaoModel(
        genreId = genreId,
        name = name,
        url = url
    )
}

fun AlbumDaoModel.toAlbum(): Album {
    return Album(
        artistId = artistId,
        artistName = artistName,
        artistUrl = artistUrl,
        artworkUrl100 = artworkUrl100,
        contentAdvisoryRating = contentAdvisoryRating,
        genres = genres.map { it.toGenre() },
        id = id,
        kind = kind,
        name = name,
        releaseDate = releaseDate,
        url = url,
    )
}

fun GenreDaoModel.toGenre(): Genre {
    return Genre(
        genreId = genreId,
        name = name,
        url = url
    )
}