package com.assem.albumsapp.util

/**
 * Created by mohamedassem
 * mohamed.assem.ali@gmail.com
 */

sealed class ErrorType {
    data class NetworkError(val message: String = "No Internet Connection!") : ErrorType()
    data class RemoteException(val message: String = "Something Wrong With the Server!") :
        ErrorType()

    data class DataIssue(val message: String = "Item Not Found!") : ErrorType()
    data class EmptyData(val message: String = "Database Is Empty!") : ErrorType()
    data class SomthingWrongHappened(val message: String = "Something Wrong Happened!") :
        ErrorType()
}
