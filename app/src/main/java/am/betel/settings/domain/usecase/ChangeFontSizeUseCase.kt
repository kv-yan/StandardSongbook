package am.betel.settings.domain.usecase

interface ChangeFontSizeUseCase{
    suspend operator fun invoke(newSize: Float)
}