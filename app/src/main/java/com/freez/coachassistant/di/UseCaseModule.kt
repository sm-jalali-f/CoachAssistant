package com.freez.coachassistant.di

import com.freez.domain.GetDaysUseCase
import com.freez.domain.UserInfoUseCase
import com.freez.domain.repositories.CalendarRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

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
}