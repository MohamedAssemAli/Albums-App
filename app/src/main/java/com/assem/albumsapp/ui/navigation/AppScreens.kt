package com.assem.albumsapp.ui.navigation

import kotlinx.serialization.Serializable

/**
 * Created by mohamedassem
 * mohamed.assem.ali@gmail.com
 */

sealed class AppScreens {

    @Serializable
    data object AlbumsListScreen : AppScreens()

    @Serializable
    data class AlbumsDetailsScreen(val albumId: String) : AppScreens()

}