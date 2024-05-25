package com.example.easylawmobile.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "access")
data class Access(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val code: String,
    val nom: String
)