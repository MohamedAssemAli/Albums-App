package com.assem.albumsapp.data.repository

import com.assem.albumsapp.data.source.remote.AlbumsRemoteSource
import com.assem.albumsapp.domain.entities.Album
import com.assem.albumsapp.data.mapper.FeedMapper
import com.assem.albumsapp.data.source.local.AlbumsLocalSource
import com.assem.albumsapp.domain.repository.AlbumsRepository
import com.assem.albumsapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AlbumsRepositoryImpl @Inject constructor(
    private val remoteSource: AlbumsRemoteSource,
    private val localSource: AlbumsLocalSource,
    private val feedMapper: FeedMapper
) : AlbumsRepository {

    override suspend fun getAlbumsFeed(fetchFromRemote: Boolean): Flow<Resource<List<Album>>> {
        return flow {
            emit(Resource.Loading(true))

            val localFeed = localSource.getAlbumsFeed()
            emit(Resource.Success(data = localFeed))

            val isCacheEmpty = localFeed.isEmpty()
            val shouldLoadCache = !isCacheEmpty && !fetchFromRemote
            if (shouldLoadCache) {
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteFeed = try {
                val response = remoteSource.getAlbumsFeed()
                feedMapper.convert(response)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("IOException => Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("HttpException => Couldn't load data"))
                null
            }

            remoteFeed?.let { feed ->
                // TODO -> uncomment after implementing caching
                /*
                localSource.clearCache()
                localSource.insertAlbumsFeed(feed)
                emit(Resource.Success(data = localSource.getAlbumsFeed()))
                 */
                emit(Resource.Success(data = feed))
                emit(Resource.Loading(false))
            }
        }
    }
}
