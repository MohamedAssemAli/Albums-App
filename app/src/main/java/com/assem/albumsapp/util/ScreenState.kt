package com.assem.albumsapp.util

sealed class ScreenState<T>(
    val data: T? = null,
    val errorType: ErrorType = ErrorType.SomthingWrongHappened(),
) {
    class Success<T>(data: T?) : ScreenState<T>(data)
    class Error<T>(errorType: ErrorType, data: T? = null) : ScreenState<T>(data, errorType)
    class Loading<T> : ScreenState<T>(null)
    class IsRefreshing<T>(val isRefreshing: Boolean = false) : ScreenState<T>(null)
}