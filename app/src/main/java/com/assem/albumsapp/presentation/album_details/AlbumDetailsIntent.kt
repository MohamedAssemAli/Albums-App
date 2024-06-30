package com.assem.albumsapp.presentation.album_details

sealed class AlbumDetailsIntent {
    data class GetAlbumById(val albumId: String) : AlbumDetailsIntent()
}
