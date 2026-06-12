package com.freez.domain.repositories

import com.freez.domain.model.AppDate
import com.freez.domain.model.ClassEvent

interface ClassSessionRepository {
    suspend fun getSessions(from: AppDate,to: AppDate): List<ClassEvent>
}