package com.freez.domain.model

import java.math.BigDecimal

data class TennisEvent(
    val date: AppDate,
    val startDateTime: Long,
    val endDateTime: Long,
    val court: TennisCourt,
    val players: List<Person>,
    val coach: Person,
    val ballBoy: Person,
    val courtRentPrice: Money,
    val ballBoyPrice: Money,
    val teachingPrice: Money,
    val eventStatus: EventStatus,
    val courtRentDiscountPercent: BigDecimal = BigDecimal.ZERO,
    val teachingFeeDiscountPercent: BigDecimal = BigDecimal.ZERO
)