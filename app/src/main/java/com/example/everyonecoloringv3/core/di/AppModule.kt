package com.example.everyonecoloringv3.core.di

import android.content.Context
import androidx.room.Room
import com.example.everyonecoloringv3.core.data.local.dao.ArtworkDao
import com.example.everyonecoloringv3.core.data.local.dao.ColoringActionDao
import com.example.everyonecoloringv3.core.data.local.dao.ColoringSessionDao
import com.example.everyonecoloringv3.core.data.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "coloring.db").build()

    @Provides
    fun provideArtworkDao(db: AppDatabase): ArtworkDao = db.artworkDao()

    @Provides
    fun provideSessionDao(db: AppDatabase): ColoringSessionDao = db.sessionDao()

    @Provides
    fun provideActionDao(db: AppDatabase): ColoringActionDao = db.actionDao()

    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}
