package com.abkhrr.gazego.core.common.utils

import kotlinx.datetime.LocalDate
import kotlinx.datetime.toJavaLocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

fun LocalDate.format(pattern: String): String =
    toJavaLocalDate().format(DateTimeFormatter.ofPattern(pattern))

internal fun Double.roundToOneDecimal() =
    (this * ONE_DECIMAL_ARGUMENT).roundToInt() / ONE_DECIMAL_ARGUMENT

private const val ONE_DECIMAL_ARGUMENT = 10.0