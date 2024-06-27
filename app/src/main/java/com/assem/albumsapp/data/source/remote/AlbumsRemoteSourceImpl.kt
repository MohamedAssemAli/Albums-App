package com.assem.albumsapp.data.source.remote

import com.assem.albumsapp.data.api.AlbumsApi
import javax.inject.Inject

class AlbumsRemoteSourceImpl @Inject constructor(private val albumsApi: AlbumsApi) :
    AlbumsRemoteSource {}
