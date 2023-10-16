package com.gnacoding.eplapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gnacoding.eplapp.domain.model.Club
import kotlinx.coroutines.flow.Flow

@Dao
interface ClubDao {
    @Query("SELECT * FROM team")
    fun getAllClubs(): Flow<List<Club>>

    @Query("SELECT * FROM team WHERE isFavorite = 1")
    fun getAllFavoriteClubs(): Flow<List<Club>>

    @Query("SELECT * FROM team WHERE id = :id")
    fun getClub(id: Int): Flow<Club>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllClub(clubList: List<Club>)

    @Query("UPDATE team SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteClub(id: Int, isFavorite: Boolean)
}