package com.assem.albumsapp.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialogDefaults.titleContentColor
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.assem.albumsapp.ui.theme.Purple40

/**
 * Created by mohamedassem
 * mohamed.assem.ali@gmail.com
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppbar(
    title: String,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
) {
    val navController = rememberNavController()
    TopAppBar(
        scrollBehavior = scrollBehavior,
        title = { Text(text = title) },
        colors = TopAppBarColors(
            containerColor = Purple40,
            scrolledContainerColor = Purple40,
            navigationIconContentColor = Color.White,
            titleContentColor = Color.White,
            actionIconContentColor = Purple40,
        ),
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        }
    )
}