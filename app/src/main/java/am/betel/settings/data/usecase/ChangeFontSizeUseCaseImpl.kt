package am.betel.settings.data.usecase

import am.betel.settings.domain.repository.SettingsRepository
import am.betel.settings.domain.usecase.ChangeFontSizeUseCase

class ChangeFontSizeUseCaseImpl(
    private val repository: SettingsRepository,
) : ChangeFontSizeUseCase {
    override suspend operator fun invoke(newSize: Float) {
        repository.updateFontSize(newSize)
    }
}

