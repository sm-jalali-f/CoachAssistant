package com.freez.repository.setting

class CalendarSettingImpl : CalendarSettingsDataSource {
    override fun getCalendarType(): CalendarType {
        // TODO: read from datastore
        return CalendarType.JALALI
    }
}