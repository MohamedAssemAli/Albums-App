package com.assem.albumsapp.presentation.albums_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.assem.albumsapp.domain.entities.Album

/**
 * Created by mohamedassem
 * mohamed.assem.ali@gmail.com
 */
@Composable
fun AlbumItem(
    album: Album,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = album.name,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = album.artistName,
            fontWeight = FontWeight.Light,
        )
    }
}