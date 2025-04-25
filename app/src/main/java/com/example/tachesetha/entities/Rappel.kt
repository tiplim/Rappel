package com.example.tachesetha.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rappel_table")
data class Rappel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String,  // Store as a String (ISO 8601 format, e.g., "2025-04-10T12:00:00")
    val description: String
)
