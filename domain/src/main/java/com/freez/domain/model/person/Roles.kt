package com.freez.domain.model.person

import com.freez.domain.model.Money

sealed interface Role {

    data class Coach(
        val licenseId: String?,
        val lastHourlyRate: Money?
    ) : Role

    data class Student(
        val skillLevel: SkillLevel?,
        val color: Long
    ) : Role

    data class BallBoy(
        val lastWage: Money?,
        val color: Long?
    ) : Role
}

