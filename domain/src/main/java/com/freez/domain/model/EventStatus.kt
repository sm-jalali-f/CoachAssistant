package com.freez.domain.model

enum class EventStatus {
    ReservedWithoutStudent, // coach reserved court but he/she hasn't student
    Reserved, // reserver by coach and has student
    Done,
    CancelByCourt,
    CancelByCoach,
    CancelByStudent,
    Sold // event is canceled and coach sold it to other person
}