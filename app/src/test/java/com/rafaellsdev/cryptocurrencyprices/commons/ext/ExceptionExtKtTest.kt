package com.rafaellsdev.cryptocurrencyprices.commons.ext

import com.rafaellsdev.cryptocurrencyprices.commons.model.DefaultError
import org.junit.Assert.assertEquals
import org.junit.Test
import java.lang.Exception

class ExceptionExtTest {

    @Test
    fun `test toDefaultError with message`() {
        val exception = Exception("Test error message")
        val defaultError = exception.toDefaultError()

        assertEquals("Test error message", defaultError.errorMessage)
    }

    @Test
    fun `test toDefaultError with null message`() {
        val exception = null
        val defaultError = exception?.toDefaultError()

        assertEquals("", defaultError?.errorMessage ?: "")
    }
}