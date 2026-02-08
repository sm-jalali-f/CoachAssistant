package com.freez.coachassistant.ui.newEvent

import androidx.lifecycle.ViewModel
import com.freez.domain.GetDaysUseCase
import com.freez.domain.model.AppDateTime
import com.freez.domain.model.EventStatus
import com.freez.domain.model.Hour
import com.freez.domain.model.Minute
import com.freez.domain.model.TennisEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class NewEventViewModel @Inject constructor(
    val getDaysUseCase: GetDaysUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(
        NewEventUiState(
            tennisEvent = TennisEvent(
                startDateTime = AppDateTime(
                    getDaysUseCase.today(),
                    Hour.of(12),
                    minute = Minute.of(0)
                ),
                endDateTime = AppDateTime(
                    getDaysUseCase.today(),
                    Hour.of(13),
                    minute = Minute.of(0)
                ),
                court = null,
                coach = null,
                ballBoy = null,
                courtRentPrice = null,
                ballBoyPrice = null,
                teachingPrice = null,
                eventStatus = EventStatus.Reserved,
            )
        )
    )
    val state: StateFlow<NewEventUiState> = _state.asStateFlow()
}