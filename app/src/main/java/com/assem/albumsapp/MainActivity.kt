package com.assem.albumsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.assem.albumsapp.presentation.album_details.AlbumDetailsScreen
import com.assem.albumsapp.presentation.albums_list.AlbumsListScreen
import com.assem.albumsapp.ui.navigation.AppScreens
import com.assem.albumsapp.ui.theme.AlbumsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            AlbumsAppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController, startDestination = AppScreens.AlbumsListScreen
                ) {
                    composable<AppScreens.AlbumsListScreen> {
                        AlbumsListScreen(navController = navController,)
                    }
                    composable<AppScreens.AlbumsDetailsScreen> {
                        val args = it.toRoute<AppScreens.AlbumsDetailsScreen>()
                        AlbumDetailsScreen(navController = navController, albumId = args.albumId)
                    }
                }
            }
        }
    }
}
