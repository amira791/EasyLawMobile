package com.example.easylawmobile.data.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "official_journal",
    indices = [Index(value = ["number", "year"], unique = true)]
)
data class OfficialJournal(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val number: Int,
    val year: Int,
    val text_file: String // Store file path as a String
)