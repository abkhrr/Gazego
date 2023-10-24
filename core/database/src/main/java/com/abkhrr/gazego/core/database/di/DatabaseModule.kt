package com.abkhrr.gazego.core.database.di

import android.content.Context
import androidx.room.Room
import com.abkhrr.gazego.core.database.GazeGoDatabase
import com.abkhrr.gazego.core.database.utils.GazegoDatabaseTransactionProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): GazeGoDatabase =
        Room.databaseBuilder(
            context,
            GazeGoDatabase::class.java,
            GazeGoDatabase.GAZEGO_DATABASE_NAME
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideMovieDao(database: GazeGoDatabase) = database.movieDao

    @Provides
    fun provideMovieDetailsDao(database: GazeGoDatabase) = database.movieDetailsDao

    @Provides
    fun provideMoviesRemoteKeysDao(database: GazeGoDatabase) = database.movieRemoteKeyDao

    @Provides
    fun provideReviewDao(database: GazeGoDatabase) = database.reviewDao

    @Provides
    fun provideReviewRemoteKeyDao(database: GazeGoDatabase) = database.reviewRemoteKeyDao

    @Provides
    fun provideWishListDao(database: GazeGoDatabase) = database.wishListDao

    @Provides
    fun provideVideoDao(database: GazeGoDatabase) = database.videoDao
}