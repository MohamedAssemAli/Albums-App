package com.assem.albumsapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import com.assem.albumsapp.R

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.assem.albumsapp.ui.theme.Pink40
import com.assem.albumsapp.util.ErrorType


@Composable
fun ErrorView(errorType: ErrorType, retryFunction: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.ic_error),
                contentDescription = "error",
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.error),
                color = Color.White,
                fontSize = 17.sp,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(8.dp))
//            Text(
//                text = errorMessage,
//                color = Color.White,
//                fontSize = 14.sp,
//                textAlign = TextAlign.Center,
//            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { retryFunction.invoke() }, colors = ButtonDefaults.buttonColors(

                    containerColor = Pink40,
                    contentColor = Color.White
                )
            ) {
                Text(stringResource(R.string.retry))
            }
        }
    }
}