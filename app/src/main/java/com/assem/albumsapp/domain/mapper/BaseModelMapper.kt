package com.assem.albumsapp.domain.mapper

interface BaseModelMapper<From, To> {
    fun convert(from: From?): To
}