package com.assem.albumsapp.presentation.albums_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assem.albumsapp.domain.repository.AlbumsRepository
import com.assem.albumsapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
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

    var state by mutableStateOf(AlbumsListState())

    init {
        getAlbumsList()
    }

    fun handleIntent(intent: AlbumsListIntent) {
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
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { state = state.copy(albums = it) }
                        }

                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }

                        is Resource.Error -> {
                            state =
                                state.copy(error = result.message ?: "Something Wrong Happened!")
                        }
                    }
                }
        }
    }
}