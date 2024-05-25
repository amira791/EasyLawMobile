package com.example.easylawmobile.data.models

import androidx.room.Entity

@Entity(primaryKeys = ["serviceId", "accessId"])
data class ServiceAccess(
    val serviceId: String,
    val accessId: Int
)