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
    var nom: String?,
    var prenom: String?,
    val role: String = "client",
    val num_telephone: String?,
    var dateNaiss: String?,
    val lieuNaiss: String?,
    var univer_Entrep: String?,
    var occupation: String?,
    val bio: String?,
    val location: String?,
    val profile_picture: String?,
    val stripeCustomerId: String
)