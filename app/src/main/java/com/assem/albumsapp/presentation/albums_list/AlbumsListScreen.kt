package com.assem.albumsapp.presentation.albums_list

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

/**
 * Created by mohamedassem
 * mohamed.assem.ali@gmail.com
 */
@Composable
fun AlbumsListScreen(
    viewModel: AlbumsListViewModel = hiltViewModel(),
    onNavigationEvent: (String) -> Unit,
) {
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = viewModel.state.isRefreshing
    )
    val state = viewModel.state

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SwipeRefresh(state = swipeRefreshState, onRefresh = {
            viewModel.handleIntent(AlbumsListIntent.RefreshList)
        }) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(vertical = 8.dp, horizontal = 4.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.albums.size) { i ->
                    val album = state.albums[i]
                    AlbumItem(album = album, onNavigationEvent = {
                        onNavigationEvent(album.id)
                    })
                }
            }
        }
    }
}