package com.gnacoding.eplapp.data.repository

import com.gnacoding.eplapp.data.local.ClubDao
import com.gnacoding.eplapp.domain.model.Club
import com.gnacoding.eplapp.domain.repository.ClubRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ClubRepositoryImpl @Inject constructor(
    private val clubDao: ClubDao,
) : ClubRepository {

    override fun getAllClubs(): Flow<List<Club>> = clubDao.getAllClubs()

    override fun getAllFavoriteClubs(): Flow<List<Club>> = clubDao.getAllFavoriteClubs()

    override fun getClub(id: Int): Flow<Club> = clubDao.getClub(id)

    override suspend fun insertAllClub(clubList: List<Club>) = clubDao.insertAllClub(clubList)

    override suspend fun updateFavoriteClub(id: Int, isFavorite: Boolean) =
        clubDao.updateFavoriteClub(id, isFavorite)
}