package com.example.easylawmobile.data.models

import androidx.room.Entity

@Entity(primaryKeys = ["userId", "domainInteretId"])
data class UserDomainInterts(
    val userId: String,
    val domainInteretId: Int
)