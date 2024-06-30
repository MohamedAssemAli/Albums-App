package com.assem.albumsapp.presentation.albums_list

import android.widget.RatingBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.assem.albumsapp.R
import com.assem.albumsapp.domain.entities.Album
import com.assem.albumsapp.ui.theme.CardBgColor

/**
 * Created by mohamedassem
 * mohamed.assem.ali@gmail.com
 */

@Composable
fun AlbumItem(
    album: Album,
    onNavigationEvent: () -> Unit,
) {
    val defaultColor = CardBgColor
    val dominantColor by remember { mutableStateOf(defaultColor) }
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .width(200.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(28.dp))
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        CardBgColor,
                        dominantColor
                    )
                )
            )
            .clickable {
                onNavigationEvent()
            }
    ) {
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
                .height(240.dp)
                .clip(RoundedCornerShape(22.dp)),
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            modifier = Modifier.padding(start = 16.dp, end = 8.dp),
            text = album.artistName,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            maxLines = 1
        )
        Text(
            modifier = Modifier.padding(start = 16.dp, end = 8.dp),
            text = album.name,
            color = Color.White,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(12.dp))
    }
}
