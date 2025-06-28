package am.betel.settings.domain.usecase

interface ChangeThemeIndexUseCase {
    suspend operator fun invoke(index: Int)
}