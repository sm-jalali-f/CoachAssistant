package com.freez.repository.di

import com.freez.datasource.database.dao.ClassSessionDao
import com.freez.datasource.database.dao.CourtDao
import com.freez.datasource.database.dao.StudentClassDao
import com.freez.datasource.database.dao.StudentDao
import com.freez.domain.repositories.CalendarRepository
import com.freez.domain.repositories.ClassSessionRepository
import com.freez.domain.repositories.LanguageRepository
import com.freez.multiCalendar.provider.CalendarProvider
import com.freez.multiCalendar.provider.GregorianCalendarProvider
import com.freez.multiCalendar.provider.JalaliCalendarProvider
import com.freez.repository.CalendarRepositoryImpl
import com.freez.repository.ClassSessionRepositoryImpl
import com.freez.repository.LanguageRepositoryImpl
import com.freez.repository.setting.CalendarSettingImpl
import com.freez.repository.setting.CalendarSettingsDataSource
import com.freez.repository.setting.CalendarType
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideJalaliCalendar(): JalaliCalendarProvider {
        return JalaliCalendarProvider()
    }

    @Provides
    fun provideGregorianCalendar(): GregorianCalendarProvider {
        return GregorianCalendarProvider()
    }

    @Provides
    fun provideCalendarSetting(): CalendarSettingsDataSource {
        return CalendarSettingImpl()
    }

    @Provides
    fun provideCalendarProvider(
        settings: CalendarSettingsDataSource,
        jalali: JalaliCalendarProvider,
        gregorian: GregorianCalendarProvider
    ): CalendarProvider {
        return when (settings.getCalendarType()) {
            CalendarType.JALALI -> jalali
            CalendarType.GREGORIAN -> gregorian
        }
    }

    @Provides
    fun provideCalendarRepository(calendarProvider: CalendarProvider): CalendarRepository {
        return CalendarRepositoryImpl(calendarProvider)
    }

    @Provides
    fun provideLanguageRepository(): LanguageRepository {
        return LanguageRepositoryImpl()
    }

    @Provides
    fun provideClassSessionRepository(
        classSessionDao: ClassSessionDao,
        courtDao: CourtDao,
        studentClassDao: StudentClassDao,
        studentDao: StudentDao,
        calendarProvider: CalendarProvider,
    ): ClassSessionRepository {
        return ClassSessionRepositoryImpl(
            classSessionDao = classSessionDao,
            courtDao = courtDao,
            studentClassDao = studentClassDao,
            studentDao = studentDao,
            calendarProvider = calendarProvider,
        )
    }
}