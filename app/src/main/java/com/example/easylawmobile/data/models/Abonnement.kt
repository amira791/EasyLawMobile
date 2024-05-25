package com.example.easylawmobile.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "abonnement")
data class Abonnement(
    @PrimaryKey
    val id: String,
    val dateDebut: String,
    val dateFin: String,
    val statut: String,
    val userId: String,
    val serviceId: String,
    val factureId: String?
)
