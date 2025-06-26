package am.betel.settings.data.usecase

import am.betel.settings.domain.repository.SettingsRepository
import am.betel.settings.domain.usecase.GetFontSizeUseCase
import kotlinx.coroutines.flow.Flow

class GetFontSizeUseCaseImpl(
    private val repository: SettingsRepository,
) : GetFontSizeUseCase {
    override operator fun invoke(): Flow<Float> = repository.getFontSizeFlow()
}