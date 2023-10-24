package com.abkhrr.gazego.core.domain

import com.abkhrr.gazego.core.common.result.ApiResult
import com.abkhrr.gazego.core.common.result.HttpException
import com.abkhrr.gazego.core.domain.mapper.asTestModel
import com.abkhrr.gazego.core.domain.repository.MovieRepository
import com.abkhrr.gazego.core.domain.usecase.GetMovieDetailsUseCase
import com.abkhrr.gazego.core.model.MovieDetails
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetMovieDetailsUseCaseTest {
    private val repository: MovieRepository = mockk()
    private val underUnitTest = GetMovieDetailsUseCase(repository)
    private val responseValidation = MovieDetails::asTestModel
    private val failure = ApiResult.failure(
        HttpException(statusCode = 500, statusMessage = "error"),
        value = null
    )

    private val itemValidate = flow<ApiResult<MovieDetails>> { ApiResult.success(responseValidation) }
    private val itemError = flow<ApiResult<MovieDetails>> { ApiResult.failure(failure.error, null) }

    @Before
    fun setUp() {
        clearAllMocks()
    }

    @Test
    fun `should get api response success`() = runBlocking {
        coEvery { repository.getById(1) } returns itemValidate
        val apiResponse = underUnitTest.invoke(1)
        Assert.assertEquals(apiResponse, itemValidate)
    }

    @Test
    fun `should get api response error`() = runBlocking {
        coEvery { repository.getById(1) } returns itemError
        val apiResponse = underUnitTest.invoke(1)
        Assert.assertEquals(apiResponse, itemError)
    }
}