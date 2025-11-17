package com.freez.datasource.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.freez.datasource.database.entity.CourtEntity

@Dao
interface CourtDao {

    @Insert
    suspend fun insert(court: CourtEntity): Long

    @Update
    suspend fun update(court: CourtEntity)

    @Query("SELECT * FROM courts")
    suspend fun getAll(): List<CourtEntity>
}
