package com.rafaellsdev.cryptocurrencyprices.commons.ext

fun Int?.intOrZero() = this ?: 0

fun Float?.floatOrZero() = this ?: 0.0

fun Double?.doubleOrZero() = this ?: 0.0