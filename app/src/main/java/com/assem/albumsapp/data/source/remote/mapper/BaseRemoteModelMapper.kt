package com.assem.albumsapp.data.source.remote.mapper

interface BaseRemoteModelMapper<From, To> {
    fun convert(from: From?): To
}