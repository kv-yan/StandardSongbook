package am.betel.settings.presentation.di

import am.betel.settings.presentation.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val settingsPresentationModule = module {
    viewModelOf(::SettingsViewModel)
}