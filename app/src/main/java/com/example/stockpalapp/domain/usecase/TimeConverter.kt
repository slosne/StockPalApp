package com.example.stockpalapp.domain.usecase

import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

class TimeConverter @Inject constructor(

){

    fun convertStringToTimestamp(dateAsString: String): Timestamp {
        val dateFormat = SimpleDateFormat("ddMMyy", Locale.getDefault())
        val date = dateFormat.parse(dateAsString)
        return Timestamp(date!!)
    }

    fun convertTimestampToString(timestamp: Timestamp): String {
        val instant = Instant.ofEpochSecond(timestamp.seconds)
        val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        return localDateTime.format(formatter)
    }
}