package com.assem.albumsapp.di

import com.assem.albumsapp.data.repository.AlbumsRepository
import com.assem.albumsapp.data.repository.AlbumsRepositoryImpl
import com.assem.albumsapp.data.source.remote.AlbumsRemoteSource
import com.assem.albumsapp.data.source.remote.AlbumsRemoteSourceImpl
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
    abstract fun bindAlbumsListingRepository(
        albumsListingRepositoryImpl: AlbumsRepositoryImpl
    ): AlbumsRepository

    @Binds
    abstract fun bindAlbumsListingRemoteSource(
        albumsListingRemoteSourceImpl: AlbumsRemoteSourceImpl
    ): AlbumsRemoteSource
}