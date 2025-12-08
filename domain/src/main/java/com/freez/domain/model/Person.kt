package com.freez.domain.model

import java.math.BigDecimal

sealed interface Role

data class Coach(
    val licenseId: String,
    val hourlyRate: BigDecimal
) : Role


data class Student(
    val membershipId: String,
    val skillLevel: String
) : Role

data class Person(val name: String, val phoneNumber: String)
