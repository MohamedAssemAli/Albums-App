package com.assem.albumsapp.data.models.dao

import io.realm.kotlin.types.EmbeddedRealmObject
import java.util.UUID


open class GenreDaoModel : EmbeddedRealmObject {

    constructor() : this(UUID.randomUUID().toString(), "", "")

    constructor(genreId: String, name: String, url: String) {
        this.genreId = genreId
        this.name = name
        this.url = url
    }


    var genreId: String = UUID.randomUUID().toString()
    var name: String = ""
    var url: String = ""
}