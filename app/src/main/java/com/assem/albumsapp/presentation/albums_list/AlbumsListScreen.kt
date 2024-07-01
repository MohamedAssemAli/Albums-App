package com.assem.albumsapp.presentation.albums_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.assem.albumsapp.R
import com.assem.albumsapp.ui.components.ErrorView
import com.assem.albumsapp.ui.components.LoadingComponent
import com.assem.albumsapp.ui.components.MyTopAppbar
import com.assem.albumsapp.ui.navigation.AppScreens
import com.assem.albumsapp.ui.theme.BackgroundColor
import com.assem.albumsapp.util.ScreenState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState

/**
 * Created by mohamedassem
 * mohamed.assem.ali@gmail.com
 */
@Composable
fun AlbumsListScreen(
    viewModel: AlbumsListViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val albumsListState = viewModel.screenState.collectAsState().value
    Scaffold(
        containerColor = BackgroundColor,
        topBar = { MyTopAppbar(title = stringResource(id = R.string.app_name)) }
    ) { paddingValues ->
        when (albumsListState) {
            is ScreenState.Loading -> {
                LoadingComponent()
            }

            is ScreenState.Idle -> {
                LoadingComponent()
            }

            is ScreenState.Error -> {
                ErrorView(errorType = albumsListState.errorType) {
                    viewModel.sendIntent(AlbumsListIntent.RefreshList)
                }
            }

            is ScreenState.Success -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    SwipeRefresh(state = SwipeRefreshState(false), onRefresh = {
                        viewModel.sendIntent(AlbumsListIntent.RefreshList)
                    }) {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            contentPadding = PaddingValues(vertical = 8.dp, horizontal = 4.dp),
                            modifier = Modifier.fillMaxSize()
                        ) {
                            albumsListState.data?.let {
                                items(it.size) { i ->
                                    val album = it[i]
                                    AlbumItem(album = album, onNavigationEvent = {
                                        navController.navigate(AppScreens.AlbumsDetailsScreen(album.id))
                                    })
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}