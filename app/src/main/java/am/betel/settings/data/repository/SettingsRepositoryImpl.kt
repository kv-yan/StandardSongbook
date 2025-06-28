package am.betel.settings.data.repository

import am.betel.settings.data.storage.SettingsDataStore
import am.betel.settings.domain.repository.SettingsRepository
import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class SettingsRepositoryImpl(private val context: Context) : SettingsRepository {
    override fun getFontSizeFlow(): Flow<Float> = SettingsDataStore.getFontSize(context).flowOn(
        Dispatchers.IO
    )

    override suspend fun updateFontSize(size: Float) {
        SettingsDataStore.saveFontSize(context, size)
    }

    override fun getThemeIndex(): Flow<Int> = SettingsDataStore.getThemeIndex(context).flowOn(
        Dispatchers.IO
    )

    override suspend fun updateThemeIndex(index: Int) {
        SettingsDataStore.saveThemeIndex(context,index)
    }
}
