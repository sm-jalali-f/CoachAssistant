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
        )
    ],
    indices = [Index("courtId")]
)
data class ClassSessionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,

    val startDateTime: Long,
    val endDateTime: Long,

    val courtId: Long?,
    val courtPrice: Long?,
    val ballBoyPrice: Long?,

    val status: SessionStatus,
    val discount: Long = 0L,

    val isTeaching: Boolean
)
