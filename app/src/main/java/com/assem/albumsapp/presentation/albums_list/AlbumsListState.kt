package com.assem.albumsapp.presentation.albums_list

import com.assem.albumsapp.domain.entities.Album

data class AlbumsListState(
    val albums: List<Album> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = "",
    val isRefreshing: Boolean = false,
)
