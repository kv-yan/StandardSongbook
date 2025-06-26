package am.betel.settings.domain.usecase

import kotlinx.coroutines.flow.Flow


interface GetFontSizeUseCase {
    operator fun invoke(): Flow<Float>
}