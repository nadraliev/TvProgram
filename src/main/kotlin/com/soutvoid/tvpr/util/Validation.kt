package com.soutvoid.tvpr.util

import com.soutvoid.tvpr.domain.genre.Genre
import com.soutvoid.tvpr.domain.show.ShowForm

fun String?.validate(): Boolean =
        !isNullOrBlank()


/**
 * Validation is passed if:
 * numerical fields are actually numerical
 * day of week is positive and not more than 6
 * specified genre actually exists
 */
fun ShowForm.validate(genres: List<Genre>): Boolean {
    try {
        hours.toLong()
        minutes.toLong()
    } catch (e: NumberFormatException) {
        return false
    }
    return name.validate() &&
            dayOfWeek in 0..6 &&
            genres.find { it.name == genreName } != null

}