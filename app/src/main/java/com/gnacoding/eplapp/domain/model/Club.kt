package com.gnacoding.eplapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "team")
data class Club(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val logo: Int,
    val name: String,
    val established: String,
    val description: String,
    val matchesPlayed: String,
    val wins: String,
    val losses: String,
    val goals: String,
    val goalsConceded: String,
    val cleanSheets: String,
    val stadiumPhoto: Int,
    val stadiumName: String,
    val stadiumDesc: String,
    val stadiumCapacity: String,
    val stadiumBuilt: String,
    val stadiumPitchSize: String,
    val stadiumAddress: String,
    val link: String,
    val isFavorite: Boolean = false
)
