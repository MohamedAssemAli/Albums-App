package com.assem.albumsapp.data.repository

import com.assem.albumsapp.data.source.remote.AlbumsRemoteSource
import com.assem.albumsapp.domain.entities.Album
import com.assem.albumsapp.data.source.remote.mapper.RemoteFeedMapper
import com.assem.albumsapp.data.source.local.AlbumsLocalSource
import com.assem.albumsapp.data.source.local.mapper.toAlbum
import com.assem.albumsapp.data.source.local.mapper.toAlbumDaoModel
import com.assem.albumsapp.domain.repository.AlbumsRepository
import com.assem.albumsapp.util.ErrorType
import com.assem.albumsapp.data.utils.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AlbumsRepositoryImpl @Inject constructor(
    private val remoteSource: AlbumsRemoteSource,
    private val localSource: AlbumsLocalSource,
    private val remoteFeedMapper: RemoteFeedMapper
) : AlbumsRepository {

    override suspend fun getAlbumsFeed(fetchFromRemote: Boolean): Flow<ResourceState<List<Album>>> {
        return flow {
            emit(ResourceState.Loading())

            val localFeed = localSource.getAlbumsList()
            if (localFeed.isNotEmpty() && !fetchFromRemote) {
                emit(ResourceState.Success(data = localFeed.map { it.toAlbum() }))
                return@flow
            }

            val remoteFeed = try {
                val response = remoteSource.getAlbumsFeed()
                remoteFeedMapper.convert(response)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(ResourceState.Error(ErrorType.NetworkError()))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(ResourceState.Error(ErrorType.RemoteException()))
                null
            }

            remoteFeed?.let { feed ->
                localSource.clearCache()
                localSource.insertAlbums(feed.map { it.toAlbumDaoModel() })
                emit(ResourceState.Success(data = localSource.getAlbumsList().map { it.toAlbum() }))
                return@flow
            } ?: run {
                emit(ResourceState.Error(ErrorType.EmptyData()))
                return@flow
            }
        }
    }

    override suspend fun getAlbumById(albumId: String): Flow<ResourceState<Album>> {
        return flow {
            emit(ResourceState.Loading())
            try {
                val localAlbum = localSource.getAlbumById(albumId)
                localAlbum?.let {
                    val album = it.toAlbum()
                    emit(ResourceState.Success(data = album))
                    return@flow
                }
            } catch (e: Exception) {
                emit(ResourceState.Error(ErrorType.DataIssue()))
                return@flow
            }
        }
    }
}
