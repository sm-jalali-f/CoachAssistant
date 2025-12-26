package com.freez.coachassistant.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freez.domain.GetDaysUseCase
import com.freez.domain.UserInfoUseCase
import com.freez.domain.model.AppDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userInfoUseCase: UserInfoUseCase,
    private val getDaysUseCase: GetDaysUseCase,
//    private val classSessionUseCase: ClassSessionUseCase
) : ViewModel() {

    private var _state: MutableStateFlow<HomeUiState> =
        MutableStateFlow(HomeUiState(loading = true))
    val state: StateFlow<HomeUiState> = _state.asStateFlow()

    init {
        onIntent(HomeIntent.LoadInitial)
    }

    fun onIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.LoadInitial -> loadInitial()
            is HomeIntent.SelectDate -> selectDate(intent.date)
        }
    }

    private fun selectDate(date: AppDate) {
        _state.value = _state.value.copy(selectedDate = date)
        // TODO: call loadSession in coroutine?
    }

    private fun loadInitial() {
        viewModelScope.launch {
            runCatching {

                _state.update { it.copy(loading = true) }

                val user = userInfoUseCase.getName()
                val days = getDaysUseCase.getDays()
                val today = getDaysUseCase.today()

                _state.update {
                    it.copy(
                        greeting = GreetingState(
                            name = user,
                            greeting = "Hi!"
                        ),
                        datesList = days,
                        today = today,
                        selectedDate = today
                    )
                }

                loadClassSessions(today)

//            }.onFailure {
//                _effect.send(HomeEffect.ShowError("Something went wrong"))
            }.also {
                _state.update { it.copy(loading = false) }
            }
        }
    }

    private fun loadClassSessions(date: AppDate) {
        // TODO:
    }
    /* private fun selectDate(date: AppDate) {
         _state.update {
             it.copy(
                 selectedDate = date,
                 loading = true
             )
         }
         loadClassSessions(date)
     }
 
     private fun loadClassSessions(date: AppDate) {
         viewModelScope.launch {
             runCatching {
                 val sessions = classSessionUseCase(date)
 
                 _state.update {
                     it.copy(
                         classSessionList = sessions,
                         loading = false,
                         error = null
                     )
                 }
 
             }.onFailure {
                 _state.update { it.copy(loading = false, error = it.message) }
                 _effect.send(HomeEffect.ShowError("Failed to load sessions"))
             }
         }
     }*/
}

//MutableStateFlow(
//HomeUiState(
//greeting = GreetingState("User", "Hi!"),
//monthlyReport = MonthlyReport(
//0,
//Money(amount = BigDecimal.ZERO, currency = Currency.getInstance("IRR"))
//)
//),
//)