package com.assem.albumsapp.presentation.album_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.assem.albumsapp.ui.components.MyTopAppbar
import com.assem.albumsapp.ui.theme.BackgroundColor
import com.assem.albumsapp.ui.theme.Pink40
import com.assem.albumsapp.ui.theme.Pink80
import com.assem.albumsapp.ui.theme.Purple80
import com.assem.albumsapp.ui.theme.PurpleGrey40
import com.assem.albumsapp.util.startBrowserIntent

/**
 * Created by mohamedassem
 * mohamed.assem.ali@gmail.com
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AlbumDetailsScreen(
    viewModel: AlbumDetailsViewModel = hiltViewModel(),
    albumId: String
) {
    viewModel.sendIntent(AlbumDetailsIntent.GetAlbumById(albumId))
    val context = LocalContext.current
    val albumDetailsState = viewModel.screenState.collectAsState().value
    val mainButtonColor = ButtonDefaults.buttonColors(
        containerColor = PurpleGrey40,
        contentColor = Color.White
    )

    Scaffold(
        containerColor = BackgroundColor,
        topBar = {
            MyTopAppbar(
                title = stringResource(id = R.string.app_name),
                hasBackNavigation = true
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Pink40)
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
                    contentScale = ContentScale.None,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clip(RoundedCornerShape(0.dp, 0.dp, 22.dp, 22.dp)),
                )
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                ) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = album.artistName,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = album.name,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(id = R.string.release_date),
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White
                    )
                    Text(
                        text = album.releaseDate,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(id = R.string.genres),
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White
                    )
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(7.dp),
                        verticalArrangement = Arrangement.spacedBy(7.dp),
                    ) {
                        album.genres.forEach {
                            Button(
                                onClick = {},
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Pink80,
                                    contentColor = Color.White
                                ),

                                ) {
                                Text(text = it.name, color = Color.White)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(id = R.string.links),
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White
                    )
                    Row {
                        Button(
                            onClick = {
                                context.startBrowserIntent(album.url)
                            },
                            colors = mainButtonColor,
                        ) {
                            Text(text = "Album Page", color = Color.White)
                            Icon(
                                imageVector = Icons.Filled.ExitToApp,
                                contentDescription = "Open Browser",
                                modifier = Modifier.padding(start = 4.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = {
                                context.startBrowserIntent(album.artistUrl)
                            },
                            colors = mainButtonColor,
                        ) {
                            Text(text = "Artist Page")
                            Icon(
                                imageVector = Icons.Filled.ExitToApp,
                                contentDescription = "Open Browser",
                                modifier = Modifier.padding(start = 4.dp)
                            )
                        }

                    }
                }
            }
        }
    }
}