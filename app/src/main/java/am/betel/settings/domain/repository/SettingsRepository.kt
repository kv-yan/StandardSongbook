package am.betel.settings.domain.repository

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    fun getFontSizeFlow(): Flow<Float>
    suspend fun updateFontSize(size: Float)
}
