package com.freez.domain.model

import java.math.BigDecimal

data class TennisEvent(
    val startDateTime: AppDateTime,
    val endDateTime: AppDateTime,
    val court: TennisCourt?,
    val players: List<Person> = emptyList(),
    val coach: Person?,
    val ballBoy: Person?,
    val courtRentPrice: Money?,
    val ballBoyPrice: Money?,
    val teachingPrice: Money?,
    val eventStatus: EventStatus = EventStatus.Reserved,
    val courtRentDiscountPercent: BigDecimal = BigDecimal.ZERO,
    val teachingFeeDiscountPercent: BigDecimal = BigDecimal.ZERO
)