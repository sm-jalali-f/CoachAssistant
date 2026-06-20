package com.freez.datasource.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "people")
data class PersonEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val phoneNumber: String,

    // Role flags
    val isCoach: Boolean = false,
    val isStudent: Boolean = false,
    val isBallBoy: Boolean = false,

    // Coach specific data
    val licenseId: String? = null,
    val lastHourlyRate: Long? = null,

    // Student specific data
    val skillLevel: Int? = null,
    val studentColor: Long? = null,
    val defaultTeachingCost: Long? = null,

    // BallBoy specific data
    val lastWage: Long? = null,
    val ballBoyColor: Long? = null
)
