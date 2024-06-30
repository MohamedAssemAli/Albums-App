package com.assem.albumsapp.presentation.album_details

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.assem.albumsapp.util.Resource

/**
 * Created by mohamedassem
 * mohamed.assem.ali@gmail.com
 */
@Composable
fun AlbumDetailsScreen(
    viewModel: AlbumDetailsViewModel = hiltViewModel(),
    albumId: String
) {
    viewModel.handleIntent(AlbumDetailsIntent.GetAlbumById(albumId))
    val albumDetailsState = viewModel.screenState.collectAsState().value

    when (albumDetailsState) {
        is Resource.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is Resource.Success -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
//                .background(PurpleGrey40)
                    .padding(16.dp)
            ) {
                albumDetailsState.data?.let { album ->
                    Text(
                        text = album.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }

        is Resource.Error -> {}
    }
}