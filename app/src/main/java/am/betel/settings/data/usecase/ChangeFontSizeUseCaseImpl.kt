package am.betel.settings.data.usecase

import am.betel.settings.domain.repository.SettingsRepository
import am.betel.settings.domain.usecase.ChangeFontSizeUseCase
import am.betel.settings.domain.usecase.ChangeThemeIndexUseCase

class ChangeFontSizeUseCaseImpl(
    private val repository: SettingsRepository,
) : ChangeFontSizeUseCase {
    override suspend operator fun invoke(newSize: Float) {
        repository.updateFontSize(newSize)
    }
}

class ChangeThemeIndexUseCaseImpl(
    private val repository: SettingsRepository,
) : ChangeThemeIndexUseCase {

    override suspend fun invoke(index: Int) {
        repository.updateThemeIndex(index)
    }
}

