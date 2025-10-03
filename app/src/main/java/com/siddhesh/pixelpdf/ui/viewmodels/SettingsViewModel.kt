package com.siddhesh.pixelpdf.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.siddhesh.pixelpdf.data.ReadingSettings
import com.siddhesh.pixelpdf.db.data.SettingsRepository
import com.siddhesh.pixelpdf.ui.theme.Theme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    val settings: StateFlow<ReadingSettings> = settingsRepository.readingSettingsFlow
        .stateIn(viewModelScope, SharingStarted.Lazily, ReadingSettings())

    fun updateTheme(theme: Theme) {
        viewModelScope.launch {
            settingsRepository.updateTheme(theme)
        }
    }

    fun updateFontSize(fontSize: Int) {
        viewModelScope.launch {
            settingsRepository.updateFontSize(fontSize)
        }
    }
}
