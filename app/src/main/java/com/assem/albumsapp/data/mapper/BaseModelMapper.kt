package com.assem.albumsapp.data.mapper

interface BaseModelMapper<From, To> {
    fun convert(from: From?): To
}