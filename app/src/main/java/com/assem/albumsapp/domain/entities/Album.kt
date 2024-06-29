package com.assem.albumsapp.domain.entities


data class Album (
    var artistId: String = "",
    var artistName: String = "",
    var artistUrl: String = "",
    var artworkUrl100: String = "",
    var contentAdvisoryRating: String = "",
    var genres: List<Genre> = emptyList(),
    var id: String = "",
    var kind: String = "",
    var name: String = "",
    var releaseDate: String = "",
    var url: String = ""
)