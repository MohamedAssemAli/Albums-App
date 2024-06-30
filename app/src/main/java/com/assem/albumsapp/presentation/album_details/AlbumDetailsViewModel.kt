package com.assem.albumsapp.presentation.album_details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assem.albumsapp.domain.entities.Album
import com.assem.albumsapp.domain.repository.AlbumsRepository
import com.assem.albumsapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
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

    private var _screenState =
        MutableStateFlow<Resource<Album>>(Resource.Loading())
    val screenState = _screenState.asStateFlow()

    fun handleIntent(intent: AlbumDetailsIntent) {
        when (intent) {
            is AlbumDetailsIntent.GetAlbumById -> {
                getAlbumById(intent.albumId)
            }
        }
    }

    private fun getAlbumById(albumId: String) {
        viewModelScope.launch {
            repository.getAlbumById(albumId)
                .catch { error ->
                    val errorMessage = error.message ?: "Somthing wrong happened"
                    _screenState.value = Resource.Error(message = errorMessage)
                    return@catch
                }
                .collectLatest { result ->
                    when (result) {
                        is Resource.Error -> {
                            val errorMessage = result.message ?: "Somthing wrong happened"
                            _screenState.value = Resource.Error(message = errorMessage)
                        }

                        is Resource.Loading -> {
                            _screenState.value = Resource.Loading()
                        }

                        is Resource.Success -> {
                            result.data?.let {
                                _screenState.value = Resource.Success(it)
                            }
                        }
                    }
                    return@collectLatest
                }
        }
    }
}