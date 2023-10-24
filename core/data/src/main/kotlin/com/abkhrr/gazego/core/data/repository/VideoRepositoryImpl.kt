package com.abkhrr.gazego.core.data.repository

import com.abkhrr.gazego.core.common.result.ApiResult
import com.abkhrr.gazego.core.data.mapper.asEntity
import com.abkhrr.gazego.core.data.mapper.asModel
import com.abkhrr.gazego.core.data.utils.listMap
import com.abkhrr.gazego.core.database.model.VideoEntity
import com.abkhrr.gazego.core.database.source.VideoDbDataSource
import com.abkhrr.gazego.core.domain.repository.VideoRepository
import com.abkhrr.gazego.core.model.Video
import com.abkhrr.gazego.core.network.flow.networkBoundResource
import com.abkhrr.gazego.core.network.source.VideoRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(
    private val dataSource: VideoRemoteDataSource,
    private val databaseDataSource: VideoDbDataSource
): VideoRepository {
    override suspend fun getVideos(movieId: Int): Flow<ApiResult<List<Video>>> =
        networkBoundResource(
            query = {
                databaseDataSource.getByMovieId(
                    movieId = movieId
                ).listMap(VideoEntity::asModel)
            },
            fetch = { dataSource.getVideos(movieId) },
            saveFetchResult = { response ->
                databaseDataSource.deleteAndInsertAll(
                    movieId = movieId,
                    videos = response.results.orEmpty().map { it.asEntity(movieId) }
                )
            }
        )
}