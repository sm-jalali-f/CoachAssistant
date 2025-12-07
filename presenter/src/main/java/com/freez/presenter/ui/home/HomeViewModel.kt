package com.freez.presenter.ui.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class HomeViewModel : ViewModel() {

    val _uiState = MutableStateFlow<HomeUiState>(HomeUiState(null, null))
}