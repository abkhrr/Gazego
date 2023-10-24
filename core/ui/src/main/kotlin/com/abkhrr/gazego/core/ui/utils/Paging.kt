package com.abkhrr.gazego.core.ui.utils

import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.abkhrr.gazego.core.model.Review
import kotlinx.coroutines.flow.flowOf

val LoadState.isLoading: Boolean get() = this is LoadState.Loading
val LoadState.isFinished: Boolean get() = this is LoadState.NotLoading
val LoadState.isError: Boolean get() = this is LoadState.Error

val LoadState.error: Throwable
    get() = (this as LoadState.Error).error

fun <T : Any> LazyPagingItems<T>.isEmpty() =
    loadState.append.endOfPaginationReached && itemCount == 0

fun <T : Any> LazyPagingItems<T>.isNotEmpty() = itemCount > 0

@Composable
fun emptyReviewsPagingItems() = flowOf(PagingData.empty<Review>()).collectAsLazyPagingItems()