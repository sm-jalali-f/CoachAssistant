package com.freez.domain.model

data class TennisCourt(
    val club: Club,
    val number: Int?
)

data class Club(
    val name: String,
    val latitude: Double?,
    val longitude: Double?,
    val rentCourt: Money?,
    val ballBoyPrice: Money?,
    val address: String?,
    val phoneNumber: String?,
    val manager: Person?
)