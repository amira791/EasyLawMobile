package com.example.easylawmobile.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "adjustment")
data class Adjustment(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val adjusted_num: String,
    val adjusting_num: String,
    val adjustment_type: String
)
