package com.abkhrr.gazego.core.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.abkhrr.gazego.core.database.converter.ListConverter
import com.abkhrr.gazego.core.database.converter.LocalDateConverter
import com.abkhrr.gazego.core.database.converter.ReviewAuthorConverter
import com.abkhrr.gazego.core.database.dao.MovieDao
import com.abkhrr.gazego.core.database.dao.MovieDetailsDao
import com.abkhrr.gazego.core.database.dao.MoviesRemoteKeysDao
import com.abkhrr.gazego.core.database.dao.ReviewDao
import com.abkhrr.gazego.core.database.dao.ReviewRemoteKeyDao
import com.abkhrr.gazego.core.database.dao.VideoDao
import com.abkhrr.gazego.core.database.dao.WishListDao
import com.abkhrr.gazego.core.database.model.MovieDetailsEntity
import com.abkhrr.gazego.core.database.model.MovieEntity
import com.abkhrr.gazego.core.database.model.MovieRemoteKeyEntity
import com.abkhrr.gazego.core.database.model.ReviewEntity
import com.abkhrr.gazego.core.database.model.ReviewRemoteKeysEntity
import com.abkhrr.gazego.core.database.model.VideoEntity
import com.abkhrr.gazego.core.database.model.WishlistEntity

@Database(
    entities = [
        MovieEntity::class,
        MovieRemoteKeyEntity:: class,
        MovieDetailsEntity::class,
        ReviewEntity::class,
        WishlistEntity::class,
        ReviewRemoteKeysEntity::class,
        VideoEntity::class
    ],
    version = 2,
)
@TypeConverters(ListConverter::class, ReviewAuthorConverter::class, LocalDateConverter::class)
abstract class GazeGoDatabase: RoomDatabase() {
    abstract val movieDao: MovieDao
    abstract val movieDetailsDao: MovieDetailsDao
    abstract val movieRemoteKeyDao: MoviesRemoteKeysDao
    abstract val reviewDao: ReviewDao
    abstract val reviewRemoteKeyDao: ReviewRemoteKeyDao
    abstract val videoDao: VideoDao
    abstract val wishListDao: WishListDao

    companion object {
        const val GAZEGO_DATABASE_NAME = "gazego.db"
    }
}