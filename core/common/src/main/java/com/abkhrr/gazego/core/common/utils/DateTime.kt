package com.abkhrr.gazego.core.common.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimePeriod
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn

object DateTime {
    fun getCurrentYear(): Int {
        val now: Instant = Clock.System.now()
        val systemTz = TimeZone.currentSystemDefault()

        return now.toLocalDateTime(systemTz).year
    }

    // Get it in yyyy-mm-dd (default by LocalDate)
    fun getCurrentDate(): LocalDate {
        return Clock.System.todayIn(TimeZone.currentSystemDefault())
    }

    // Get it in yyyy-mm-dd (default by LocalDate)
    fun getCurrentDateString(): String {
        return getCurrentDate().toString()
    }

    fun getFutureDate(plus: Int = 1): LocalDate {
        val now: Instant = Clock.System.now()
        val systemTz = TimeZone.currentSystemDefault()

        return now.plus(DateTimePeriod(years = plus), systemTz).toLocalDateTime(systemTz).date
    }

    fun getFutureDateString(plus: Int = 1): String {
        return getFutureDate(plus = 1).toString()
    }
}