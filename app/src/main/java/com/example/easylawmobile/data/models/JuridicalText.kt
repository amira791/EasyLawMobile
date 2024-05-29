package com.example.easylawmobile.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "juridical_text",
    foreignKeys = [ForeignKey(
        entity = OfficialJournal::class,
        parentColumns = ["id"],
        childColumns = ["official_journal_id"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["official_journal_id"])]
)
data class JuridicalText(
    @PrimaryKey
    val id_text: String,
    val type_text: String,
    val signature_date: String?, // Store dates as Strings in ISO format
    val publication_date: String?,
    val jt_number: String?,
    val source: String?,
    val official_journal_id: Int,
    val official_journal_page: Int,
    val description: String?,
    val text_file: String?, // Store file path as a String
    val extracted_text: String?
)
