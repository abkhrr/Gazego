package com.abkhrr.gazego.core.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingData
import com.abkhrr.gazego.core.common.ext.emptyString
import com.abkhrr.gazego.core.data.mapper.asEntity
import com.abkhrr.gazego.core.data.mapper.asModel
import com.abkhrr.gazego.core.data.mapper.asNetworkCategory
import com.abkhrr.gazego.core.data.paging.MovieRemoteMediator
import com.abkhrr.gazego.core.data.paging.SearchMoviePagingSource
import com.abkhrr.gazego.core.data.utils.defaultPagingConfig
import com.abkhrr.gazego.core.data.utils.listMap
import com.abkhrr.gazego.core.data.utils.pagingMap
import com.abkhrr.gazego.core.database.model.MovieEntity
import com.abkhrr.gazego.core.database.source.MovieDbDataSource
import com.abkhrr.gazego.core.database.source.MovieDetailsDbDataSource
import com.abkhrr.gazego.core.database.source.WishlistDbDataSource
import com.abkhrr.gazego.core.domain.repository.MovieRepository
import com.abkhrr.gazego.core.model.Movie
import com.abkhrr.gazego.core.model.MovieCategory
import com.abkhrr.gazego.core.network.flow.networkBoundResource
import com.abkhrr.gazego.core.network.model.NetworkMovieDetail
import com.abkhrr.gazego.core.network.source.MovieRemoteDataSource
import com.abkhrr.gazego.core.network.util.PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class MovieRepositoryImpl @Inject constructor(
    private val dataSource: MovieRemoteDataSource,
    private val movieDatabaseDataSource: MovieDbDataSource,
    private val detailDatabaseDataSource: MovieDetailsDbDataSource,
    private val wishlistDatabaseDataSource: WishlistDbDataSource
) : MovieRepository {
    override fun getByCategory(
        category: MovieCategory.Categories,
        primaryReleaseYear: Int,
        primaryMinReleaseDate: String,
        primaryMaxReleaseDate: String
    ) = networkBoundResource(
            query = {
                movieDatabaseDataSource.getByCategory(
                    category = category,
                    pageSize = PAGE_SIZE
                ).listMap(MovieEntity::asModel)
            },
            fetch = { dataSource.getByCategory(
                category = category.asNetworkCategory(),
                primaryReleaseYear = primaryReleaseYear,
                primaryMinReleaseDate = primaryMinReleaseDate,
                primaryMaxReleaseDate = primaryMaxReleaseDate
            )},
            saveFetchResult = { response ->
                movieDatabaseDataSource.deleteByCategoryThenInsertAll(
                    category = category,
                    movies = response.results.map { it.asEntity(category) }
                )
            }
        )

    override fun getPagingByCategory(category: MovieCategory.Categories): Flow<PagingData<Movie>> {
        return Pager(
            config = defaultPagingConfig,
            remoteMediator = MovieRemoteMediator(movieDatabaseDataSource, dataSource, category),
            pagingSourceFactory = { movieDatabaseDataSource.getPagingByMediaType(category) }
        ).flow.pagingMap(MovieEntity::asModel)
    }

    override fun search(query: String) = Pager(
        config = defaultPagingConfig,
        pagingSourceFactory = { SearchMoviePagingSource(query, dataSource) }
    ).flow

    override suspend fun getById(id: Int) = networkBoundResource(
        query = {
            detailDatabaseDataSource.getById(id).map {
                it?.asModel(
                    isOnWishlist = wishlistDatabaseDataSource.isWishlisted(it.id),
                )
            }
        },
        fetch = { dataSource.getById(id) },
        saveFetchResult = { response ->
            detailDatabaseDataSource.deleteAndInsert(response.asEntity())
        }
    )

    override suspend fun getByIds(ids: List<Int>) = networkBoundResource(
        query = {
            detailDatabaseDataSource.getByIds(ids).listMap {
                it.asModel(
                    isOnWishlist = wishlistDatabaseDataSource.isWishlisted(it.id)
                )
            }
        },
        fetch = { dataSource.getByIds(ids) },
        saveFetchResult = { response ->
            detailDatabaseDataSource.deleteAndInsertAll(
                response.map(NetworkMovieDetail::asEntity)
            )
        }
    )
}