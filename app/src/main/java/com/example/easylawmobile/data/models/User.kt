package com.example.easylawmobile.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey
    val username: String,
    val password: String,
    val email: String,
    val etat: String?,
    val nom: String?,
    val prenom: String?,
    val role: String = "client",
    val num_telephone: String?,
    val dateNaiss: String?,
    val lieuNaiss: String?,
    val univer_Entrep: String?,
    val occupation: String?,
    val bio: String?,
    val location: String?,
    val profile_picture: String?,
    val stripeCustomerId: String
)