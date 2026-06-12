package com.freez.domain

import com.freez.domain.model.AppDate
import com.freez.domain.model.ClassEvent
import com.freez.domain.repositories.ClassSessionRepository

class GetClassSessionUseCase constructor(
    private val classSessionRepository: ClassSessionRepository
) {
    suspend operator fun invoke(from: AppDate, to: AppDate): List<ClassEvent> {
        return classSessionRepository.getSessions(from = from, to = to)
    }
}