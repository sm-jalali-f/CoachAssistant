package com.freez.coachassistant.ui.newEvent

import androidx.lifecycle.ViewModel
import com.freez.domain.GetDaysUseCase
import com.freez.domain.model.AppDate
import com.freez.domain.repositories.CalendarRepository
import com.freez.domain.model.AppDateTime
import com.freez.domain.model.ClassEvent
import com.freez.domain.model.Club
import com.freez.domain.model.EventStatus
import com.freez.domain.model.Hour
import com.freez.domain.model.Minute
import com.freez.domain.model.TennisCourt
import com.freez.domain.model.person.BallBoyPerson
import com.freez.domain.model.person.Person
import com.freez.domain.model.person.Role
import com.freez.domain.model.person.StudentPerson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class NewEventViewModel @Inject constructor(
    private val getDaysUseCase: GetDaysUseCase,
    private val calendarRepository: CalendarRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(createInitialState())
    val state: StateFlow<NewEventUiState> = _state.asStateFlow()

    init {
        onIntent(NewEventIntent.LoadInitial)
    }

    fun onIntent(intent: NewEventIntent) {
        when (intent) {
            NewEventIntent.LoadInitial -> loadInitial()
            is NewEventIntent.ShowDialog -> showDialog(intent.dialog)
            NewEventIntent.DismissDialog -> _state.update { it.copy(activeDialog = null) }
            is NewEventIntent.SelectDate -> selectDate(intent.date)
            is NewEventIntent.SelectDateFromPicker -> selectDateFromPicker(intent.year, intent.month, intent.day)
            is NewEventIntent.SelectStartTime -> updateStartTime(intent.hour, intent.minute)
            is NewEventIntent.SelectEndTime -> updateEndTime(intent.hour, intent.minute)
            is NewEventIntent.ConfirmPlayers -> confirmPlayers(intent.players)
            is NewEventIntent.RemovePlayer -> removePlayer(intent.player)
            is NewEventIntent.UpdateTeachingCost -> updateTeachingCost(intent.value)
            is NewEventIntent.SelectClub -> selectClub(intent.club)
            is NewEventIntent.UpdateClubRentCost -> updateClubRentCost(intent.value)
            is NewEventIntent.SelectBallBoy -> selectBallBoy(intent.ballBoy)
            is NewEventIntent.UpdateBallBoyCost -> updateBallBoyCost(intent.value)
            is NewEventIntent.SelectStatus -> selectStatus(intent.status)
            is NewEventIntent.SetReserveWeeks -> setReserveWeeks(intent.weeks)
            NewEventIntent.Save -> save()
        }
    }

    private fun loadInitial() {
        val today = getDaysUseCase.today()
        _state.update {
            it.copy(
                availablePlayers = samplePlayers(),
                availableClubs = sampleClubs(),
                availableBallBoys = sampleBallBoys(),
                tennisEvent = it.tennisEvent.copy(
                    startDateTime = it.tennisEvent.startDateTime.copy(appDate = today),
                    endDateTime = it.tennisEvent.endDateTime.copy(appDate = today),
                    players = emptyList(),
                    eventStatus = EventStatus.ReservedWithoutStudent,
                ),
            )
        }
    }

    private fun showDialog(dialog: NewEventDialog) {
        _state.update { it.copy(activeDialog = dialog) }
    }

    private fun selectDateFromPicker(year: Int, month: Int, day: Int) {
        val appDate = calendarRepository.getAroundDays(
            selectDate = AppDate(year, month, day, "", ""),
            previousDaysCount = 0,
            nextDaysCount = 0,
        ).first()
        selectDate(appDate)
    }

    private fun selectDate(date: AppDate) {
        _state.update { current ->
            current.copy(
                activeDialog = null,
                tennisEvent = current.tennisEvent.copy(
                    startDateTime = current.tennisEvent.startDateTime.copy(appDate = date),
                    endDateTime = current.tennisEvent.endDateTime.copy(appDate = date),
                ),
            )
        }
    }

    private fun updateStartTime(hour: Hour, minute: Minute) {
        _state.update { current ->
            current.copy(
                activeDialog = null,
                tennisEvent = current.tennisEvent.copy(
                    startDateTime = current.tennisEvent.startDateTime.copy(
                        hour = hour,
                        minute = minute,
                    ),
                ),
            )
        }
    }

    private fun updateEndTime(hour: Hour, minute: Minute) {
        _state.update { current ->
            current.copy(
                activeDialog = null,
                tennisEvent = current.tennisEvent.copy(
                    endDateTime = current.tennisEvent.endDateTime.copy(
                        hour = hour,
                        minute = minute,
                    ),
                ),
            )
        }
    }

    private fun confirmPlayers(players: List<StudentPerson>) {
        _state.update { current ->
            val status = if (current.statusManuallySet) {
                current.tennisEvent.eventStatus
            } else {
                defaultStatusForPlayers(players)
            }
            current.copy(
                activeDialog = null,
                tennisEvent = current.tennisEvent.copy(
                    players = players,
                    eventStatus = status,
                ),
            )
        }
    }

    private fun removePlayer(player: StudentPerson) {
        _state.update { current ->
            val players = current.tennisEvent.players.filter { it != player }
            val status = if (current.statusManuallySet) {
                current.tennisEvent.eventStatus
            } else {
                defaultStatusForPlayers(players)
            }
            current.copy(
                tennisEvent = current.tennisEvent.copy(
                    players = players,
                    eventStatus = status,
                ),
            )
        }
    }

    private fun updateTeachingCost(value: Long?) {
        _state.update { it.copy(teachingCost = value) }
    }

    private fun selectClub(club: Club) {
        _state.update { current ->
            current.copy(
                activeDialog = null,
                selectedClub = club,
                tennisEvent = current.tennisEvent.copy(
                    court = TennisCourt(club = club, number = null),
                ),
            )
        }
    }

    private fun updateClubRentCost(value: Long?) {
        _state.update { it.copy(clubRentCost = value) }
    }

    private fun selectBallBoy(ballBoy: BallBoyPerson?) {
        _state.update { current ->
            current.copy(
                activeDialog = null,
                tennisEvent = current.tennisEvent.copy(ballBoy = ballBoy),
            )
        }
    }

    private fun updateBallBoyCost(value: Long?) {
        _state.update { it.copy(ballBoyCost = value ?: 0L) }
    }

    private fun selectStatus(status: EventStatus) {
        _state.update { current ->
            current.copy(
                activeDialog = null,
                statusManuallySet = true,
                tennisEvent = current.tennisEvent.copy(eventStatus = status),
            )
        }
    }

    private fun setReserveWeeks(weeks: Int) {
        _state.update { it.copy(reserveWeeks = weeks.coerceAtLeast(1)) }
    }

    private fun save() {
        // TODO: persist event via repository
    }

    private fun defaultStatusForPlayers(players: List<StudentPerson>): EventStatus =
        if (players.isEmpty()) EventStatus.ReservedWithoutStudent else EventStatus.Reserved

    private fun createInitialState(): NewEventUiState {
        val today = getDaysUseCase.today()
        return NewEventUiState(
            tennisEvent = ClassEvent(
                startDateTime = AppDateTime(today, Hour.of(12), Minute.of(0)),
                endDateTime = AppDateTime(today, Hour.of(13), Minute.of(0)),
                court = null,
                players = emptyList(),
                coach = null,
                ballBoy = null,
                courtRentPrice = null,
                ballBoyPrice = null,
                teachingPrice = null,
                eventStatus = EventStatus.ReservedWithoutStudent,
            ),
        )
    }

    private fun samplePlayers(): List<StudentPerson> = listOf(
        student("علی رضایی", 0xFF4CAF50),
        student("سارا احمدی", 0xFF2196F3),
        student("محمد کریمی", 0xFFFF9800),
        student("نازنین موسوی", 0xFFE91E63),
    )

    private fun sampleClubs(): List<Club> = listOf(
        Club("باشگاه تنیس آزادی", null, null, null, null, "تهران", null, null),
        Club("باشگاه ورزشی پارس", null, null, null, null, "تهران", null, null),
        Club("مجموعه ورزشی انقلاب", null, null, null, null, "تهران", null, null),
    )

    private fun sampleBallBoys(): List<BallBoyPerson> = listOf(
        ballBoy("رضا محمدی"),
        ballBoy("امیر حسینی"),
        ballBoy("پویا نوری"),
    )

    private fun student(name: String, color: Long): StudentPerson =
        StudentPerson.from(
            Person(name, "", setOf(Role.Student(skillLevel = null, color = color)))
        )!!

    private fun ballBoy(name: String): BallBoyPerson =
        BallBoyPerson.from(
            Person(name, "", setOf(Role.BallBoy(lastWage = null, color = null)))
        )!!
}
