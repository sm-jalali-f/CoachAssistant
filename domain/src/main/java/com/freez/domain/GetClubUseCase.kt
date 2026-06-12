package com.freez.domain

import com.freez.domain.model.Club
import com.freez.domain.repositories.ClubRepository

class GetClubUseCase constructor(
    private val clubRepository: ClubRepository
) {
    suspend fun getClubs(): List<Club> {
        return clubRepository.getClubs()
    }
}