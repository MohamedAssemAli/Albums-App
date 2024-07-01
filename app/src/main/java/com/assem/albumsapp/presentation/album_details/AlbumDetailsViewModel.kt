package com.assem.albumsapp.presentation.album_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assem.albumsapp.domain.entities.Album
import com.assem.albumsapp.domain.repository.AlbumsRepository
import com.assem.albumsapp.util.ErrorType
import com.assem.albumsapp.data.utils.ResourceState
import com.assem.albumsapp.util.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by mohamedassem
 * mohamed.assem.ali@gmail.com
 */

@HiltViewModel
class AlbumDetailsViewModel @Inject constructor(
    private val repository: AlbumsRepository
) : ViewModel() {

    private var _screenState = MutableStateFlow<ScreenState<Album>>(ScreenState.Loading())
    val screenState = _screenState.asStateFlow()

    fun sendIntent(intent: AlbumDetailsIntent) {
        when (intent) {
            is AlbumDetailsIntent.GetAlbumById -> {
                getAlbumById(intent.albumId)
            }
        }
    }

    private fun getAlbumById(albumId: String) {
        viewModelScope.launch {
            repository.getAlbumById(albumId).catch { error ->
                _screenState.value = ScreenState.Error(
                    errorType = ErrorType.SomthingWrongHappened(
                        error.message ?: "Something Wrong Happened!"
                    )
                )
                return@catch
            }.collect { result ->
                when (result) {
                    is ResourceState.Error -> {
                        _screenState.value = ScreenState.Error(
                            errorType = result.errorType ?: ErrorType.SomthingWrongHappened()
                        )
                    }

                    is ResourceState.Loading -> {
                        _screenState.value = ScreenState.Loading()
                    }

                    is ResourceState.Success -> {
                        result.data?.let {
                            _screenState.value = ScreenState.Success(it)
                        }
                    }
                }
            }
        }
    }
}