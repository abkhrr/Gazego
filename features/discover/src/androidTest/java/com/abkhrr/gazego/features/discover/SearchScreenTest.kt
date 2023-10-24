package com.abkhrr.gazego.features.discover

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.abkhrr.gazego.core.common.ext.emptyDouble
import com.abkhrr.gazego.core.common.ext.emptyInt
import com.abkhrr.gazego.core.common.ext.emptyString
import com.abkhrr.gazego.core.model.Movie
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var searchPlaceholder: String
    private lateinit var clearSearchContentDesc: String

    @Before
    fun setup() {
        composeTestRule.activity.apply {
            searchPlaceholder = getString(R.string.searchPlaceholder)
            clearSearchContentDesc = getString(R.string.clearSearch)
        }
    }

    @Test
    fun searchTextField_isFocused() {
        composeTestRule.setContent {
            SearchScreen(
                uiState = SearchUiState(
                    isSearching = true
                ),
                searchMovies = flowOf(
                    PagingData.from(
                    listOf(
                        Movie(
                            id = emptyInt(),
                            title = emptyString(),
                            overview = emptyString(),
                            releaseDate = null,
                            genreIds = emptyList(),
                            voteAverage = emptyDouble(),
                            popularity = emptyDouble(),
                            posterPath = emptyString(),
                            backdropPath = emptyString()
                        )
                    )
                )).collectAsLazyPagingItems(),
                onRetry = {},
                onMovieClick = {},
                onRefresh = {},
                onOfflineModeClick = {},
                onQueryChange = {}
            )
        }

        composeTestRule
            .onNodeWithTag("searchTextField")
            .assertIsFocused()
    }

    @Test
    fun emptyQuery_notEmptyRecentSearches_clearSearchesButton_displayed() {
        composeTestRule.setContent {
            SearchScreen(
                uiState = SearchUiState(
                    query = "Not Empty",
                    isSearching = true
                ),
                searchMovies = flowOf(
                    PagingData.from(
                        listOf(
                            Movie(
                                id = emptyInt(),
                                title = emptyString(),
                                overview = emptyString(),
                                releaseDate = null,
                                genreIds = emptyList(),
                                voteAverage = emptyDouble(),
                                popularity = emptyDouble(),
                                posterPath = emptyString(),
                                backdropPath = emptyString()
                            )
                        )
                    )).collectAsLazyPagingItems(),
                onRetry = {},
                onMovieClick = {},
                onRefresh = {},
                onOfflineModeClick = {},
                onQueryChange = {}
            )
        }

        composeTestRule
            .onNodeWithContentDescription(clearSearchContentDesc)
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText("Not Empty")
            .assertIsDisplayed()
    }

    @Test
    fun searchResultWithMovies_firstMoviesResourcesIsVisible() {
        composeTestRule.setContent {
            SearchScreen(
                uiState = SearchUiState(
                    query = "Not Empty",
                    isSearching = true,
                    searchMovies = flowOf(
                        PagingData.from(
                            listOf(
                                Movie(
                                    id = emptyInt(),
                                    title = "Title",
                                    overview = emptyString(),
                                    releaseDate = null,
                                    genreIds = emptyList(),
                                    voteAverage = emptyDouble(),
                                    popularity = emptyDouble(),
                                    posterPath = emptyString(),
                                    backdropPath = emptyString()
                                )
                            )
                        ))
                ),
                searchMovies = flowOf(
                    PagingData.from(
                        listOf(
                            Movie(
                                id = emptyInt(),
                                title = "Title",
                                overview = emptyString(),
                                releaseDate = null,
                                genreIds = emptyList(),
                                voteAverage = emptyDouble(),
                                popularity = emptyDouble(),
                                posterPath = emptyString(),
                                backdropPath = emptyString()
                            )
                        )
                    )).collectAsLazyPagingItems(),
                onRetry = {},
                onMovieClick = {},
                onRefresh = {},
                onOfflineModeClick = {},
                onQueryChange = {}
            )
        }

        composeTestRule
            .onNodeWithText("Title")
            .assertIsDisplayed()
    }

}