package com.abkhrr.gazego.core.database.utils

import androidx.room.withTransaction
import com.abkhrr.gazego.core.database.GazeGoDatabase
import javax.inject.Inject

class GazegoDatabaseTransactionProvider @Inject constructor(
    private val gazegoDatabase: GazeGoDatabase
) {
    suspend fun <R> runWithTransaction(block: suspend () -> R) =
        gazegoDatabase.withTransaction(block)
}