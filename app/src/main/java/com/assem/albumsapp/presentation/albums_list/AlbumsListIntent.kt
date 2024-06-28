package com.assem.albumsapp.presentation.albums_list

sealed class AlbumsListIntent {
    data object RefreshList : AlbumsListIntent()
}
