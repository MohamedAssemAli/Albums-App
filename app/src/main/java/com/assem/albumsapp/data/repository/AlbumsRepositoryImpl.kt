package com.assem.albumsapp.data.repository

import com.assem.albumsapp.data.source.remote.AlbumsRemoteSource
import com.assem.albumsapp.domain.entities.Album
import com.assem.albumsapp.data.source.remote.mapper.RemoteFeedMapperRemote
import com.assem.albumsapp.data.source.local.AlbumsLocalSource
import com.assem.albumsapp.data.source.local.mapper.toAlbum
import com.assem.albumsapp.data.source.local.mapper.toAlbumDaoModel
import com.assem.albumsapp.domain.repository.AlbumsRepository
import com.assem.albumsapp.util.ErrorType
import com.assem.albumsapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AlbumsRepositoryImpl @Inject constructor(
    private val remoteSource: AlbumsRemoteSource,
    private val localSource: AlbumsLocalSource,
    private val remoteFeedMapper: RemoteFeedMapperRemote
) : AlbumsRepository {

    override suspend fun getAlbumsFeed(fetchFromRemote: Boolean): Flow<Resource<List<Album>>> {
        return flow {
            emit(Resource.Loading())
            val localFeed = localSource.getAlbumsList()
            emit(Resource.Success(data = localFeed.map { it.toAlbum() }))

            if (localFeed.isNotEmpty() && !fetchFromRemote) {
                return@flow
            }

            val remoteFeed = try {
                val response = remoteSource.getAlbumsFeed()
                remoteFeedMapper.convert(response)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(ErrorType.NetworkError()))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(ErrorType.RemoteException()))
                null
            }

            remoteFeed?.let { feed ->
                localSource.clearCache()
                localSource.insertAlbums(feed.map { it.toAlbumDaoModel() })
                emit(Resource.Success(data = localSource.getAlbumsList().map { it.toAlbum() }))
                return@flow
            } ?: run {
                emit(Resource.Error(ErrorType.EmptyData()))
                return@flow
            }
        }
    }

    override suspend fun getAlbumById(albumId: String): Flow<Resource<Album>> {
        return flow {
            emit(Resource.Loading())
            try {
                val localAlbum = localSource.getAlbumById(albumId)
                localAlbum?.let {
                    val album = it.toAlbum()
                    emit(Resource.Success(data = album))
                    return@flow
                }
            } catch (e: Exception) {
                emit(Resource.Error(ErrorType.DataIssue()))
                return@flow
            }
        }
    }
}
