package com.freez.domain

import com.freez.domain.model.AppLanguage
import com.freez.domain.repositories.LanguageRepository
import kotlinx.coroutines.flow.Flow

class ObserverLanguageUseCase(private val repository: LanguageRepository) {

    operator fun invoke(): Flow<AppLanguage> = repository.observeLanguage()
}