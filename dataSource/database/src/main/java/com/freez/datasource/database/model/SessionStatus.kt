package com.freez.datasource.database.model

enum class SessionStatus {
    EMPTY_RESERVE, // without student reserved
    RESERVE,
    EXECUTED,
    CANCEL_BY_COURT,
    CANCEL_BY_COACH,
    CANCEL_BY_STUDENT,
    SOLD
}