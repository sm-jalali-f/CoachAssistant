package com.freez.repository

import com.freez.domain.model.AppLanguage
import com.freez.domain.repositories.LanguageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

const val LANG_KEY = "language_key"


class LanguageRepositoryImpl @Inject constructor(
    // TODO: Use Data Store
) : LanguageRepository {
    var language = AppLanguage.Persian
    override fun observeLanguage(): Flow<AppLanguage> = flow {
        emit(language)
    }


    override suspend fun setLanguage(language: AppLanguage) {
        this.language = language
    }
}
