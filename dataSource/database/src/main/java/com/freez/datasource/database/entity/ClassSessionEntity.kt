package com.freez.datasource.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.freez.datasource.database.model.SessionStatus

@Entity(
    tableName = "class_sessions",
    foreignKeys = [
        ForeignKey(
            entity = CourtEntity::class,
            parentColumns = ["id"],
            childColumns = ["courtId"],
            onDelete = ForeignKey.SET_NULL
        ),
        ForeignKey(
            entity = PersonEntity::class,
            parentColumns = ["id"],
            childColumns = ["coachId"],
            onDelete = ForeignKey.SET_NULL
        ),
        ForeignKey(
            entity = PersonEntity::class,
            parentColumns = ["id"],
            childColumns = ["ballBoyId"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [
        Index("courtId"),
        Index("coachId"),
        Index("ballBoyId")
    ]
)
data class ClassSessionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,

    val startDateTime: Long,
    val endDateTime: Long,

    val courtId: Long?,
    val courtPrice: Long?,
    
    val coachId: Long?,
    val ballBoyId: Long?,
    val ballBoyPrice: Long?,

    val status: SessionStatus,
    val discount: Long = 0L,

    val isTeaching: Boolean
)
