package com.freez.datasource.database

import androidx.room.TypeConverter
import com.freez.datasource.database.model.SessionStatus

class Converters {
    @TypeConverter
    fun fromStatus(status: SessionStatus): String = status.name

    @TypeConverter
    fun toStatus(value: String): SessionStatus = SessionStatus.valueOf(value)
}