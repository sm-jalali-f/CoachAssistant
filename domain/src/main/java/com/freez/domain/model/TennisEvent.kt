package com.freez.domain.model

import com.freez.domain.model.person.BallBoyPerson
import com.freez.domain.model.person.CoachPerson
import com.freez.domain.model.person.Person
import com.freez.domain.model.person.StudentPerson
import java.math.BigDecimal

data class TennisEvent(
    val startDateTime: AppDateTime,
    val endDateTime: AppDateTime,
    val court: TennisCourt?,
    val players: List<StudentPerson> = emptyList(),
    val coach: CoachPerson?,
    val ballBoy: BallBoyPerson?,
    val courtRentPrice: Money?,
    val ballBoyPrice: Money?,
    val teachingPrice: Money?,
    val eventStatus: EventStatus = EventStatus.Reserved,
    val courtRentDiscountPercent: BigDecimal = BigDecimal.ZERO,
    val teachingFeeDiscountPercent: BigDecimal = BigDecimal.ZERO
)