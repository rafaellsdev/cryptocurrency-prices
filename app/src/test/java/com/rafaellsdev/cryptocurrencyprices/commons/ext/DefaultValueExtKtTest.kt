package com.rafaellsdev.cryptocurrencyprices.commons.ext

import org.junit.Assert.assertEquals
import org.junit.Test

class DefaultValueExtTest {

    @Test
    fun `test doubleOrZero with non-null value`() {
        val value = 5.5
        val result = value.doubleOrZero()
        assertEquals(5.5, result, 0.0)
    }

    @Test
    fun `test doubleOrZero with null value`() {
        val value: Double? = null
        val result = value.doubleOrZero()
        assertEquals(0.0, result, 0.0)
    }
}