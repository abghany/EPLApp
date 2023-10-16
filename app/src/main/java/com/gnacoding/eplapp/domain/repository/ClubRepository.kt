package com.gnacoding.eplapp.domain.repository

import com.gnacoding.eplapp.domain.model.Club
import kotlinx.coroutines.flow.Flow

interface ClubRepository {
    fun getAllClubs(): Flow<List<Club>>

    fun getAllFavoriteClubs(): Flow<List<Club>>

    fun getClub(id: Int): Flow<Club>

    suspend fun insertAllClub(clubList: List<Club>)

    suspend fun updateFavoriteClub(id: Int, isFavorite: Boolean)
}