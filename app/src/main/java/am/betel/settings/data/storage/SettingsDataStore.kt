package am.betel.settings.data.storage


import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object SettingsDataStore {
    private val Context.dataStore by preferencesDataStore("app_settings")
    private val FONT_SIZE_KEY = floatPreferencesKey("font_size")
    private val THEME_INDEX_KEY = intPreferencesKey("theme_index")

    fun getFontSize(context: Context): Flow<Float> =
        context.dataStore.data
            .map { it[FONT_SIZE_KEY] ?: 16f } // Default to 16sp

    suspend fun saveFontSize(context: Context, size: Float) {
        context.dataStore.edit {
            it[FONT_SIZE_KEY] = size
        }
    }

    fun getThemeIndex(context: Context): Flow<Int> =
        context.dataStore.data
            .map { it[THEME_INDEX_KEY] ?: 0 } // Default to 16sp

    suspend fun saveThemeIndex(context: Context, index: Int) {
        context.dataStore.edit {
            it[THEME_INDEX_KEY] = index
        }
    }
}
