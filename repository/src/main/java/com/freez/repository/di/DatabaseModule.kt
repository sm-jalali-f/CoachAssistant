package com.freez.repository.di

import android.content.Context
import androidx.room.Room
import com.freez.datasource.database.AppDatabase
import com.freez.datasource.database.dao.ClassSessionDao
import com.freez.datasource.database.dao.CourtDao
import com.freez.datasource.database.dao.StudentClassDao
import com.freez.datasource.database.dao.StudentDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "coach_assistant.db",
        ).build()

    @Provides
    fun provideClassSessionDao(database: AppDatabase): ClassSessionDao =
        database.classSessionDao()

    @Provides
    fun provideCourtDao(database: AppDatabase): CourtDao =
        database.courtDao()

    @Provides
    fun provideStudentClassDao(database: AppDatabase): StudentClassDao =
        database.studentClassDao()

    @Provides
    fun provideStudentDao(database: AppDatabase): StudentDao =
        database.studentDao()
}
