package com.freez.coachassistant.util

import com.freez.domain.model.EventStatus

fun EventStatus.persianLabel(): String = when (this) {
    EventStatus.ReservedWithoutStudent -> "رزرو بدون شاگرد"
    EventStatus.Reserved -> "رزرو شده"
    EventStatus.Done -> "برگزار شد"
    EventStatus.CancelByCourt -> "لغو توسط باشگاه"
    EventStatus.CancelByCoach -> "لغو توسط مربی"
    EventStatus.CancelByStudent -> "لغو توسط شاگرد"
    EventStatus.Sold -> "فروخته شد"
}
