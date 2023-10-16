package com.gnacoding.eplapp.di

import android.app.Application
import androidx.room.Room
import com.gnacoding.eplapp.data.local.ClubDao
import com.gnacoding.eplapp.data.local.ClubDatabase
import com.gnacoding.eplapp.data.repository.ClubRepositoryImpl
import com.gnacoding.eplapp.domain.repository.ClubRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application) = Room
        .databaseBuilder(app, ClubDatabase::class.java, "clubs.db")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideTeamDao(clubDb: ClubDatabase) = clubDb.clubDao()

    @Provides
    @Singleton
    fun provideClubRepository(clubDao: ClubDao): ClubRepository {
        return ClubRepositoryImpl(clubDao)
    }
}