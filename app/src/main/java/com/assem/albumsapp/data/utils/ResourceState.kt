package com.assem.albumsapp.data.utils

import com.assem.albumsapp.util.ErrorType

sealed class ResourceState<T>(val data: T? = null, val errorType: ErrorType? = null) {
    class Success<T>(data: T?) : ResourceState<T>(data)
    class Error<T>(errorType: ErrorType, data: T? = null) : ResourceState<T>(data, errorType)
    class Loading<T> : ResourceState<T>(null)
}
