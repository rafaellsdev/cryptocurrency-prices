package com.rafaellsdev.cryptocurrencyprices.commons.model

import org.junit.Assert.assertEquals
import org.junit.Test

class DefaultErrorTest {

    @Test
    fun `test DefaultError initialization`() {
        val errorMessage = "An error occurred"
        val defaultError = DefaultError(errorMessage)

        assertEquals(errorMessage, defaultError.errorMessage)
    }
}