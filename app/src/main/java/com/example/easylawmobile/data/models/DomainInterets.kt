package com.example.easylawmobile.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "domain_interets")
data class DomainInterets(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nom: String
)