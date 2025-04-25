package com.example.tachesetha.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class DateUtils {
    companion object {
        fun convertDateToString(date: LocalDateTime) : String { // Get the current date and time
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm") // Define the format pattern
            val formattedDate = date.format(formatter) // Format the date and time
            return formattedDate;
        }
    }
}