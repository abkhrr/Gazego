package com.abkhrr.gazego.core.ui.mapper

import com.abkhrr.gazego.core.model.Genre

fun Genre.asGenreName() = Genre(id, name).name

fun Genre.asGenre() = Genre(id, name)

fun Genre.asGenreId() = Genre(id, name).id