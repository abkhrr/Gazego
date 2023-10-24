package com.abkhrr.gazego.core.domain.usecase

import com.abkhrr.gazego.core.domain.repository.MovieRepository
import com.abkhrr.gazego.core.domain.repository.WishListRepository
import com.abkhrr.gazego.core.model.WishList
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

class GetAllWishlistedMoviesUseCase @Inject constructor(
    private val wishListRepository: WishListRepository,
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke() = wishListRepository.getAll().flatMapLatest { wishlist ->
        val ids = wishlist.map(WishList::id)
        movieRepository.getByIds(ids)
    }
}