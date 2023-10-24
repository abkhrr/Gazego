package com.abkhrr.gazego.di

import com.abkhrr.gazego.core.data.repository.MovieRepositoryImpl
import com.abkhrr.gazego.core.data.repository.ReviewRepositoryImpl
import com.abkhrr.gazego.core.data.repository.VideoRepositoryImpl
import com.abkhrr.gazego.core.data.repository.WishlistRepositoryImpl
import com.abkhrr.gazego.core.domain.repository.MovieRepository
import com.abkhrr.gazego.core.domain.repository.ReviewRepository
import com.abkhrr.gazego.core.domain.repository.VideoRepository
import com.abkhrr.gazego.core.domain.repository.WishListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {
    @ViewModelScoped
    @Binds
    fun bindMovieRepository(repositoryImpl: MovieRepositoryImpl) : MovieRepository

    @ViewModelScoped
    @Binds
    fun bindReviewRepository(repositoryImpl: ReviewRepositoryImpl) : ReviewRepository

    @ViewModelScoped
    @Binds
    fun bindWishlistRepository(repositoryImpl: WishlistRepositoryImpl) : WishListRepository

    @ViewModelScoped
    @Binds
    fun bindVideoRepository(repositoryImpl: VideoRepositoryImpl) : VideoRepository
}