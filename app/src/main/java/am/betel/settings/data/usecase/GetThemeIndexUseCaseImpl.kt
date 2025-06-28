package am.betel.settings.data.usecase

import am.betel.settings.domain.repository.SettingsRepository
import am.betel.settings.domain.usecase.GetThemeIndexUseCase
import kotlinx.coroutines.flow.Flow

class GetThemeIndexUseCaseImpl(
    private val repository: SettingsRepository,
) : GetThemeIndexUseCase {
    override operator fun invoke(): Flow<Int> = repository.getThemeIndex()
}