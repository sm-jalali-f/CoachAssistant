package com.freez.coachassistant.di

import com.freez.domain.GetDaysUseCase
import com.freez.domain.ObserverLanguageUseCase
import com.freez.domain.UserInfoUseCase
import com.freez.domain.repositories.CalendarRepository
import com.freez.domain.repositories.LanguageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {
    @Provides
    fun provideGetUserUseCase(
    ): UserInfoUseCase {
        return UserInfoUseCase()
    }

    @Provides
    fun provideGetDaysUseCase(
        calendarRepository: CalendarRepository
    ): GetDaysUseCase {
        return GetDaysUseCase(calendarRepository)
    }

    @Provides
    fun provideObserveAppLanguageUseCase(
        repository: LanguageRepository
    ): ObserverLanguageUseCase {
        return ObserverLanguageUseCase(repository)
    }
}