package com.abkhrr.gazego.core.domain.usecase

import com.abkhrr.gazego.core.domain.repository.WishListRepository
import javax.inject.Inject

class RemoveMovieFromWishlistUseCase @Inject constructor(
    private val repository: WishListRepository
) {
    suspend operator fun invoke(id: Int) = repository.remove(id)
}