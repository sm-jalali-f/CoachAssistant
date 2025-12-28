package com.freez.domain.repositories

import com.freez.domain.model.AppLanguage
import kotlinx.coroutines.flow.Flow

interface LanguageRepository {

    fun observeLanguage(): Flow<AppLanguage>
    suspend fun setLanguage(language: AppLanguage)
}