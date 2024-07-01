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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.assem.albumsapp.ui.theme.ButtonBgColor
import com.assem.albumsapp.util.ErrorType


@Composable
fun ErrorView(errorType: ErrorType, retryFunction: () -> Unit) {
    val errorIconAndMessagePair = getErrorIconAndMessage(errorType)

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
                painter = painterResource(errorIconAndMessagePair.first),
                contentDescription = "error",
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = stringResource(R.string.error),
                color = Color.White,
                fontSize = 17.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = errorIconAndMessagePair.second,
                color = Color.White,
                fontSize = 17.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { retryFunction.invoke() }, colors = ButtonDefaults.buttonColors(
                    containerColor = ButtonBgColor,
                    contentColor = Color.White
                ),
                modifier = Modifier.width(120.dp)
            ) {
                Text(stringResource(R.string.retry))
            }
        }
    }
}

private fun getErrorIconAndMessage(errorType: ErrorType): Pair<Int, String> {
    return when (errorType) {
        is ErrorType.DataIssue -> {
            Pair(R.drawable.ic_error_no_found, errorType.message)
        }

        is ErrorType.EmptyData -> {
            Pair(R.drawable.ic_error_no_found, errorType.message)
        }

        is ErrorType.NetworkError -> {
            Pair(R.drawable.ic_error_no_internet, errorType.message)
        }

        is ErrorType.RemoteException -> {
            Pair(R.drawable.ic_error, errorType.message)
        }

        is ErrorType.SomthingWrongHappened -> {
            Pair(R.drawable.ic_error, errorType.message)
        }
    }
}