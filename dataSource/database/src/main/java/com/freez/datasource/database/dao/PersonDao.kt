package com.freez.datasource.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.freez.datasource.database.entity.PersonEntity

@Dao
interface PersonDao {

    @Insert
    suspend fun insert(person: PersonEntity): Long

    @Update
    suspend fun update(person: PersonEntity)

    @Delete
    suspend fun delete(person: PersonEntity)

    @Query("SELECT * FROM people ORDER BY name")
    suspend fun getAll(): List<PersonEntity>

    @Query("SELECT * FROM people WHERE id = :id")
    suspend fun getById(id: Long): PersonEntity?

    @Query("SELECT * FROM people WHERE id IN (:ids)")
    suspend fun getByIds(ids: List<Long>): List<PersonEntity>

    @Query("SELECT * FROM people WHERE isCoach = 1 ORDER BY name")
    suspend fun getCoaches(): List<PersonEntity>

    @Query("SELECT * FROM people WHERE isStudent = 1 ORDER BY name")
    suspend fun getStudents(): List<PersonEntity>

    @Query("SELECT * FROM people WHERE isBallBoy = 1 ORDER BY name")
    suspend fun getBallBoys(): List<PersonEntity>
}
