package com.assem.albumsapp.ui.components

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.assem.albumsapp.ui.theme.BackgroundColor
import com.assem.albumsapp.ui.theme.Purple40
import com.assem.albumsapp.ui.theme.Purple80

/**
 * Created by mohamedassem
 * mohamed.assem.ali@gmail.com
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppbar(
    title: String,
    hasBackNavigation: Boolean = false
) {
    val navController = rememberNavController()
    TopAppBar(
        scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
        title = { Text(text = title) },
        colors = TopAppBarColors(
            containerColor = Purple80,
            scrolledContainerColor = BackgroundColor,
            navigationIconContentColor = Color.White,
            titleContentColor = Color.White,
            actionIconContentColor = Color.White,
        ),
        navigationIcon = {
            if (hasBackNavigation) {
                IconButton(onClick = {
                    Log.d("Assem", "MyTopAppbar: ")
                    navController.popBackStack()
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