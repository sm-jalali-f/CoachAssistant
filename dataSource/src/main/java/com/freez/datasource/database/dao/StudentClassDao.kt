package com.freez.datasource.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.freez.datasource.database.entity.StudentClassEntity

@Dao
interface StudentClassDao {

    @Insert
    suspend fun insert(sc: StudentClassEntity): Long

    @Query("SELECT * FROM student_classes WHERE sessionId = :sessionId")
    suspend fun getStudentsOfSession(sessionId: Long): List<StudentClassEntity>

    @Query("SELECT * FROM student_classes WHERE studentId = :studentId")
    suspend fun getSessionsOfStudent(studentId: Long): List<StudentClassEntity>
}
