package com.freez.domain.repositories

import com.freez.domain.model.Club

interface ClubRepository {
    suspend fun getClubs(): List<Club>
}