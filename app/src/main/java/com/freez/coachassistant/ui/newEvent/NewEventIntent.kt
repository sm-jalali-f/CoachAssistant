package com.freez.coachassistant.ui.newEvent

import com.freez.domain.model.AppDate
import com.freez.domain.model.Club
import com.freez.domain.model.EventStatus
import com.freez.domain.model.Hour
import com.freez.domain.model.Minute
import com.freez.domain.model.person.BallBoyPerson
import com.freez.domain.model.person.StudentPerson

sealed interface NewEventIntent {
    data object LoadInitial : NewEventIntent
    data class ShowDialog(val dialog: NewEventDialog) : NewEventIntent
    data object DismissDialog : NewEventIntent
    data class SelectDate(val date: AppDate) : NewEventIntent
    data class SelectDateFromPicker(val year: Int, val month: Int, val day: Int) : NewEventIntent
    data class SelectStartTime(val hour: Hour, val minute: Minute) : NewEventIntent
    data class SelectEndTime(val hour: Hour, val minute: Minute) : NewEventIntent
    data class ConfirmPlayers(val players: List<StudentPerson>) : NewEventIntent
    data class RemovePlayer(val player: StudentPerson) : NewEventIntent
    data class UpdateTeachingCost(val value: Long?) : NewEventIntent
    data class SelectClub(val club: Club) : NewEventIntent
    data class UpdateClubRentCost(val value: Long?) : NewEventIntent
    data class SelectBallBoy(val ballBoy: BallBoyPerson?) : NewEventIntent
    data class UpdateBallBoyCost(val value: Long?) : NewEventIntent
    data class SelectStatus(val status: EventStatus) : NewEventIntent
    data class SetReserveWeeks(val weeks: Int) : NewEventIntent
    data object Save : NewEventIntent
}
