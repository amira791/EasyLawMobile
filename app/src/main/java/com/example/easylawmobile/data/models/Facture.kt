package com.example.easylawmobile.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "facture")
data class Facture(
    @PrimaryKey
    val id: String,
    val date: String,  // Date should be stored as String in ISO format (e.g., "2023-05-23")
    val montant: Float,
    val montantPayé: Float,
    val payé: Boolean,
    val methodeDePayment: String?,
    val pdf: String
)