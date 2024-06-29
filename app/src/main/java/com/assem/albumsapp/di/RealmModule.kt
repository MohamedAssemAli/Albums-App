package com.assem.albumsapp.di

import android.content.Context
import com.assem.albumsapp.data.models.dao.AlbumDaoModel
import com.assem.albumsapp.data.models.dao.GenreDaoModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

/**
 * Created by mohamedassem
 * mohamed.assem.ali@gmail.com
 */

@InstallIn(SingletonComponent::class)
@Module
object RealmModule {
    @Provides
    @Singleton
    fun provideRealm(): Realm {
        val realmConfig = RealmConfiguration.create(
            schema = setOf(
                AlbumDaoModel::class,
                GenreDaoModel::class,
            ),
        )
        return Realm.open(realmConfig)
    }
}