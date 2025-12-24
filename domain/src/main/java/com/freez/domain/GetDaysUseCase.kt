package com.freez.domain

import com.freez.domain.model.AppDate

interface GetDaysUseCase {

    fun getDays(): List<AppDate>
    fun today(): AppDate
}