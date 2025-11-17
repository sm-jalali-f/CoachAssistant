package com.freez.datasource.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.freez.datasource.database.entity.StudentPaymentEntity

@Dao
interface StudentPaymentDao {

    @Insert
    suspend fun insert(payment: StudentPaymentEntity): Long

    @Query("SELECT * FROM student_payments WHERE studentId = :studentId ORDER BY date DESC")
    suspend fun getPaymentsOfStudent(studentId: Long): List<StudentPaymentEntity>
}
