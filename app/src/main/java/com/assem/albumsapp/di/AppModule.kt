package com.assem.albumsapp.di

import com.assem.albumsapp.data.repository.AlbumsRepositoryImpl
import com.assem.albumsapp.data.source.local.AlbumsLocalSource
import com.assem.albumsapp.data.source.local.AlbumsLocalSourceImpl
import com.assem.albumsapp.data.source.local.dao.AlbumDao
import com.assem.albumsapp.data.source.local.dao.AlbumDaoImpl
import com.assem.albumsapp.data.source.remote.AlbumsRemoteSource
import com.assem.albumsapp.data.source.remote.AlbumsRemoteSourceImpl
import com.assem.albumsapp.domain.repository.AlbumsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindAlbumsListingRepository(albumsListingRepositoryImpl: AlbumsRepositoryImpl): AlbumsRepository

    @Binds
    abstract fun bindAlbumsListingRemoteSource(albumsListingRemoteSourceImpl: AlbumsRemoteSourceImpl): AlbumsRemoteSource

    @Binds
    abstract fun bindAlbumDao(albumDaoImpl: AlbumDaoImpl): AlbumDao

    @Binds
    abstract fun bindAlbumsListingLocalSource(albumsListingLocalSourceImpl: AlbumsLocalSourceImpl): AlbumsLocalSource
}