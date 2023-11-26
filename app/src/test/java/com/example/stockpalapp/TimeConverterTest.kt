package com.example.stockpalapp

import com.example.stockpalapp.domain.usecase.TimeConverter
import com.google.firebase.Timestamp
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Date

class TimeConverterTest {

    private val timeConverter = TimeConverter()

    @Test
    fun `convertStringToTimestamp should convert string to timestamp`() {
        val dateAsString = "010123" // assuming "01.01.2023"
        val expectedTimestamp = Timestamp(Date(2023 - 1900, 0, 1))

        val actualTimestamp = timeConverter.convertStringToTimestamp(dateAsString)

        assertEquals(expectedTimestamp, actualTimestamp)
    }

    @Test
    fun `convertTimestampToString should convert timestamp to string`() {
        val timestamp = Timestamp(Date(2023 - 1900, 0, 1))
        val expectedDateString = "01.01.2023"

        val actualDateString = timeConverter.convertTimestampToString(timestamp)

        assertEquals(expectedDateString, actualDateString)
    }

}