package com.freez.datasource.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.freez.datasource.database.entity.ClassSessionEntity

@Dao
interface ClassSessionDao {

    @Insert
    suspend fun insert(session: ClassSessionEntity): Long

    @Update
    suspend fun update(session: ClassSessionEntity)

    @Query("SELECT * FROM class_sessions WHERE id = :id")
    suspend fun getById(id: Long): ClassSessionEntity?

    @Query("""
        SELECT * FROM class_sessions 
        WHERE startDateTime BETWEEN :from AND :to
        ORDER BY startDateTime
    """)
    suspend fun getSessionsInRange(from: Long, to: Long): List<ClassSessionEntity>
}
