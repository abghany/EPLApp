package com.gnacoding.eplapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gnacoding.eplapp.domain.model.Club

@Database(entities = [Club::class], version = 1, exportSchema = false)
abstract class ClubDatabase : RoomDatabase() {
    abstract fun clubDao(): ClubDao
}