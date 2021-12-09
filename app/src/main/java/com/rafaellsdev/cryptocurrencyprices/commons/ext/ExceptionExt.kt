package com.rafaellsdev.cryptocurrencyprices.commons.ext

import com.rafaellsdev.cryptocurrencyprices.commons.model.DefaultError
import java.lang.Exception

fun Exception.toDefaultError() : DefaultError =
    DefaultError(
        errorMessage = this.message.orEmpty()
    )