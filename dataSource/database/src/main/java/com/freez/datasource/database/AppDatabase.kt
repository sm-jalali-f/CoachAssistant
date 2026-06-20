package com.freez.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.freez.datasource.database.dao.ClassSessionDao
import com.freez.datasource.database.dao.CourtDao
import com.freez.datasource.database.dao.PersonDao
import com.freez.datasource.database.dao.StudentClassDao
import com.freez.datasource.database.dao.StudentPaymentDao
import com.freez.datasource.database.entity.ClassSessionEntity
import com.freez.datasource.database.entity.CourtEntity
import com.freez.datasource.database.entity.PersonEntity
import com.freez.datasource.database.entity.StudentClassEntity
import com.freez.datasource.database.entity.StudentPaymentEntity

@Database(
    entities = [
        PersonEntity::class,
        CourtEntity::class,
        ClassSessionEntity::class,
        StudentClassEntity::class,
        StudentPaymentEntity::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun personDao(): PersonDao
    abstract fun courtDao(): CourtDao
    abstract fun classSessionDao(): ClassSessionDao
    abstract fun studentClassDao(): StudentClassDao
    abstract fun studentPaymentDao(): StudentPaymentDao
}
