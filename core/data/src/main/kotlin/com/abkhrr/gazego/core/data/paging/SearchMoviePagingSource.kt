package com.abkhrr.gazego.core.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.abkhrr.gazego.core.common.result.HttpException
import com.abkhrr.gazego.core.common.result.isFailure
import com.abkhrr.gazego.core.common.result.isSuccess
import com.abkhrr.gazego.core.data.mapper.asModel
import com.abkhrr.gazego.core.model.Movie
import com.abkhrr.gazego.core.network.model.NetworkMovie
import com.abkhrr.gazego.core.network.source.MovieRemoteDataSource
import com.abkhrr.gazego.core.network.util.DEFAULT_PAGE
import java.io.IOException

class SearchMoviePagingSource(
    private val query: String,
    private val networkDataSource: MovieRemoteDataSource
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>) = state.anchorPosition

    @Suppress("ReturnCount")
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val currentPage = params.key ?: DEFAULT_PAGE
            val response = networkDataSource.search(query = query, page = currentPage)

            when {
                response.isSuccess() -> {
                    val data = response.value.results.map(NetworkMovie::asModel)
                    val endOfPaginationReached = data.isEmpty()

                    val prevPage = if (currentPage == 1) null else currentPage - 1
                    val nextPage = if (endOfPaginationReached) null else currentPage + 1

                    LoadResult.Page(
                        data = data,
                        prevKey = prevPage,
                        nextKey = nextPage
                    )
                }
                response.isFailure() -> return LoadResult.Error(response.error)
                else -> error("Unhandled State: $response")
            }
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}