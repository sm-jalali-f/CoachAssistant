package com.freez.coachassistant.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freez.domain.ObserverLanguageUseCase
import com.freez.domain.model.AppLanguage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val observerLanguageUseCase: ObserverLanguageUseCase
) : ViewModel() {
    val appLanguage: StateFlow<AppLanguage> =
        observerLanguageUseCase().stateIn(
            scope = viewModelScope,
            started = SharingStarted.Companion.Eagerly,
            initialValue = AppLanguage.Persian
        )
}