package com.freez.coachassistant.ui.home

import androidx.lifecycle.ViewModel
import com.freez.domain.model.Money
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.math.BigDecimal
import java.util.Currency

class HomeViewModel : ViewModel() {

    private val _uiState =
        MutableStateFlow(
            HomeUiState(
                greeting = GreetingState("User", "Hi!"),
                monthlyReport = MonthlyReport(
                    0,
                    Money(amount = BigDecimal.ZERO, currency = Currency.getInstance("IRR"))
                )
            ),
        )
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()


}