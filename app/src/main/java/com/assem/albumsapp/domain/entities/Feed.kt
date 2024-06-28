package com.assem.albumsapp.domain.entities


data class Feed(
    var author: Author = Author(),
    var copyright: String = "",
    var country: String = "",
    var icon: String = "",
    var id: String = "",
    var links: List<Link> = emptyList(),
    var albums: List<Album> = emptyList(),
    var title: String = "",
    var updated: String = ""
)