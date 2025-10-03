package com.siddhesh.pixelpdf.db.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.siddhesh.pixelpdf.data.ReadingSettings
import com.siddhesh.pixelpdf.ui.theme.Theme
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsRepository @Inject constructor(@ApplicationContext private val context: Context) {

    private object PreferencesKeys {
        val THEME = stringPreferencesKey("theme")
        val FONT_SIZE = intPreferencesKey("font_size")
    }

    val readingSettingsFlow: Flow<ReadingSettings> = context.dataStore.data.map {
        mapReadingSettings(it)
    }

    suspend fun updateTheme(theme: Theme) {
        context.dataStore.edit {
            it[PreferencesKeys.THEME] = theme.name
        }
    }

    suspend fun updateFontSize(fontSize: Int) {
        context.dataStore.edit {
            it[PreferencesKeys.FONT_SIZE] = fontSize
        }
    }

    private fun mapReadingSettings(preferences: Preferences): ReadingSettings {
        val theme = Theme.valueOf(preferences[PreferencesKeys.THEME] ?: Theme.LIGHT.name)
        val fontSize = preferences[PreferencesKeys.FONT_SIZE] ?: 16
        return ReadingSettings(theme = theme, fontSize = fontSize)
    }
}
