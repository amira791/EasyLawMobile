package com.example.easylawmobile.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "service")
data class Service(
    @PrimaryKey
    val id: String,
    val nom: String,
    val description: String,
    val tarif: Float,
    val priceId: String,
    val accesses: List<Access>
)