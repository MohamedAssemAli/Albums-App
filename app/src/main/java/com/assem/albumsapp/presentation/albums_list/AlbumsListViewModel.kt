package com.assem.albumsapp.presentation.albums_list

import android.util.Log
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
class AlbumsListViewModel @Inject constructor(
    private val repository: AlbumsRepository
) : ViewModel() {

    private var _screenState =
        MutableStateFlow<ScreenState<List<Album>>>(ScreenState.Idle())
    val screenState = _screenState.asStateFlow()

    init {
        getAlbumsList()
    }

    fun sendIntent(intent: AlbumsListIntent) {
        when (intent) {
            AlbumsListIntent.RefreshList -> {
                getAlbumsList(fetchFromRemote = true)
            }
        }
    }

    private fun getAlbumsList(
        fetchFromRemote: Boolean = false
    ) {
        viewModelScope.launch {
            repository
                .getAlbumsFeed(fetchFromRemote)
                .catch { error ->
                    _screenState.value =
                        ScreenState.Error(errorType = ErrorType.SomthingWrongHappened(error.message))
                    return@catch
                }
                .collect { result ->
                    Log.d("Assem", "getAlbumsList: " + result.toString())
                    when (result) {
                        is ResourceState.Success -> {
                            result.data?.let {
                                _screenState.value = ScreenState.Success(it)
                            }
                        }

                        is ResourceState.Loading -> {
                            _screenState.value = ScreenState.Loading()
                        }

                        is ResourceState.Error -> {
                            _screenState.value = ScreenState.Error(
                                errorType = result.errorType ?: ErrorType.SomthingWrongHappened()
                            )
                        }
                    }
                }
        }
    }
}