package com.abkhrr.gazego.core.domain.usecase

import com.abkhrr.gazego.core.common.result.ApiResult
import com.abkhrr.gazego.core.domain.repository.VideoRepository
import com.abkhrr.gazego.core.model.Video
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetVideoUseCase @Inject constructor(
    private val repository: VideoRepository
) {
    suspend operator fun invoke(movieId: Int): Flow<ApiResult<List<Video>>> =
        repository.getVideos(movieId)
}