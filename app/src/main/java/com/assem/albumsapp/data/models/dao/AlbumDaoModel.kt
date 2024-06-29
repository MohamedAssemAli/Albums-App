package com.assem.albumsapp.data.models.dao

import com.assem.albumsapp.domain.entities.Genre
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import java.util.UUID

open class AlbumDaoModel : RealmObject {
    constructor() : this(
        UUID.randomUUID().toString(),
        "",
        "",
        "",
        "",
        "",
        realmListOf(),
        "",
        "",
        "",
        ""
    )

    constructor(
        id: String,
        artistId: String,
        artistName: String,
        artistUrl: String,
        artworkUrl100: String,
        contentAdvisoryRating: String,
        genres: RealmList<GenreDaoModel>,
        kind: String,
        name: String,
        releaseDate: String,
        url: String,
    ) {
        this.id = id
        this.artistId = artistId
        this.artistName = artistName
        this.artistUrl = artistUrl
        this.artworkUrl100 = artworkUrl100
        this.contentAdvisoryRating = contentAdvisoryRating
        this.genres = genres
        this.kind = kind
        this.name = name
        this.releaseDate = releaseDate
        this.url = url
    }

    @PrimaryKey
    var id: String = UUID.randomUUID().toString()
    var artistId: String = ""
    var artistName: String = ""
    var artistUrl: String = ""
    var artworkUrl100: String = ""
    var contentAdvisoryRating: String = ""
    var genres: RealmList<GenreDaoModel> = realmListOf()
    var kind: String = ""
    var name: String = ""
    var releaseDate: String = ""
    var url: String = ""
}

