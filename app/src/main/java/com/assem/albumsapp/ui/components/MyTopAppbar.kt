package com.assem.albumsapp.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.assem.albumsapp.ui.theme.BackgroundColor
import com.assem.albumsapp.ui.theme.PrimaryColor

/**
 * Created by mohamedassem
 * mohamed.assem.ali@gmail.com
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppbar(
    title: String,
    hasBackNavigation: Boolean = false,
    navController: NavHostController? = null
) {

    TopAppBar(
        scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
        title = { Text(text = title) },
        colors = TopAppBarColors(
            containerColor = PrimaryColor,
            scrolledContainerColor = BackgroundColor,
            navigationIconContentColor = Color.White,
            titleContentColor = Color.White,
            actionIconContentColor = Color.White,
        ),
        navigationIcon = {
            if (hasBackNavigation) {
                IconButton(onClick = {
                    navController?.navigateUp()
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        }
    )
}