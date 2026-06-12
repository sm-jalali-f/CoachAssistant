package com.freez.domain.repositories

import com.freez.domain.model.ClassEvent

interface ClassSessionRepository {
    suspend fun getSessions(): List<ClassEvent>
}