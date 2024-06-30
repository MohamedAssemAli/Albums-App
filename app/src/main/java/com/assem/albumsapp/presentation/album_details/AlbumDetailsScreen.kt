package com.assem.albumsapp.presentation.album_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.assem.albumsapp.R
import com.assem.albumsapp.presentation.albums_list.AlbumItem
import com.assem.albumsapp.presentation.albums_list.AlbumsListIntent
import com.assem.albumsapp.ui.components.ErrorView
import com.assem.albumsapp.ui.components.LoadingComponent
import com.assem.albumsapp.ui.components.MyTopAppbar
import com.assem.albumsapp.ui.theme.PurpleGrey40
import com.assem.albumsapp.util.ScreenState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState

/**
 * Created by mohamedassem
 * mohamed.assem.ali@gmail.com
 */
@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AlbumDetailsScreen(
    viewModel: AlbumDetailsViewModel = hiltViewModel(),
    albumId: String
) {
    viewModel.sendIntent(AlbumDetailsIntent.GetAlbumById(albumId))
    val albumDetailsState = viewModel.screenState.collectAsState().value

    Scaffold(
        topBar = { MyTopAppbar(title = stringResource(id = R.string.app_name)) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(PurpleGrey40)
                .padding(paddingValues)
        ) {
            albumDetailsState.data?.let { album ->
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(album.artworkUrl100)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.album_placeholder),
                    contentDescription = album.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp)
                        .height(250.dp)
                        .clip(RoundedCornerShape(22.dp)),
                )
                Text(
                    text = album.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = album.artistName,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = album.releaseDate,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))


                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(7.dp),
                    verticalArrangement = Arrangement.spacedBy(7.dp),
                    modifier = Modifier
                        .padding(7.dp)
                ) {
                    Text(text = "")
                    Text(text = "genre")
//                        album.genres.forEach { genre ->
//                            Text(text = genre)
////                            Text(
////                                text = genre,
////                                modifier = Modifier
////                                    .background(color = Color.Gray, shape = CircleShape)
////                                    .padding(vertical = 3.dp, horizontal = 5.dp)
////                            )
//                        }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

/*
    when (albumDetailsState) {
        is ScreenState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is ScreenState.Success -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(PurpleGrey40)
                    .padding(16.dp)
            ) {
                albumDetailsState.data?.let { album ->
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(album.artworkUrl100)
                            .crossfade(true)
                            .build(),
                        placeholder = painterResource(R.drawable.album_placeholder),
                        contentDescription = album.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(6.dp)
                            .height(250.dp)
                            .clip(RoundedCornerShape(22.dp)),
                    )
                    Text(
                        text = album.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = album.artistName,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = album.releaseDate,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))


                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(7.dp),
                        verticalArrangement = Arrangement.spacedBy(7.dp),
                        modifier = Modifier
                            .padding(7.dp)
                    ) {
                        Text(text = "")
                        Text(text = "genre")
//                        album.genres.forEach { genre ->
//                            Text(text = genre)
////                            Text(
////                                text = genre,
////                                modifier = Modifier
////                                    .background(color = Color.Gray, shape = CircleShape)
////                                    .padding(vertical = 3.dp, horizontal = 5.dp)
////                            )
//                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }

        is ScreenState.Error -> {}
        is ScreenState.Idle -> {}
    }

 */