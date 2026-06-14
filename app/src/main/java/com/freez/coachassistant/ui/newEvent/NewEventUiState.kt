package com.freez.coachassistant.ui.newEvent

import com.freez.domain.model.AppDate
import com.freez.domain.model.ClassEvent
import com.freez.domain.model.Club
import com.freez.domain.model.EventStatus
import com.freez.domain.model.person.BallBoyPerson
import com.freez.domain.model.person.StudentPerson

data class NewEventUiState(
    val tennisEvent: ClassEvent,
    val availablePlayers: List<StudentPerson> = emptyList(),
    val availableClubs: List<Club> = emptyList(),
    val availableBallBoys: List<BallBoyPerson> = emptyList(),
    val teachingCost: Long? = null,
    val clubRentCost: Long? = null,
    val ballBoyCost: Long = 0L,
    val reserveWeeks: Int = 1,
    val selectedClub: Club? = null,
    val activeDialog: NewEventDialog? = null,
    val statusManuallySet: Boolean = false,
)

enum class NewEventDialog {
    DatePicker,
    StartTimePicker,
    EndTimePicker,
    PlayerPicker,
    ClubPicker,
    BallBoyPicker,
    StatusPicker,
}
