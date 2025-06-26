package am.betel.settings.data.di

import am.betel.settings.data.usecase.ChangeFontSizeUseCaseImpl
import am.betel.settings.data.usecase.GetFontSizeUseCaseImpl
import am.betel.settings.domain.repository.SettingsRepository
import am.betel.settings.data.repository.SettingsRepositoryImpl
import am.betel.settings.domain.usecase.ChangeFontSizeUseCase
import am.betel.settings.domain.usecase.GetFontSizeUseCase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val settingsDataModule = module {
    singleOf(::GetFontSizeUseCaseImpl) { bind<GetFontSizeUseCase>() }
    singleOf(::ChangeFontSizeUseCaseImpl) { bind<ChangeFontSizeUseCase>() }
    singleOf(::SettingsRepositoryImpl) { bind<SettingsRepository>() }
}