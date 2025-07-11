package am.betel.settings.presentation

import am.betel.settings.domain.model.AppTheme
import am.betel.settings.domain.usecase.ChangeFontSizeUseCase
import am.betel.settings.domain.usecase.ChangeThemeIndexUseCase
import am.betel.settings.domain.usecase.GetFontSizeUseCase
import am.betel.settings.domain.usecase.GetThemeIndexUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SettingsViewModel(
    getFontSizeUseCase: GetFontSizeUseCase,
    private val changeFontSizeUseCase: ChangeFontSizeUseCase,
    getThemeIndexUseCase: GetThemeIndexUseCase,
    private val changeThemeIndexUseCase: ChangeThemeIndexUseCase,
) : ViewModel() {

    private val _fontSize = MutableStateFlow(16f)
    val fontSize: StateFlow<Float> = _fontSize

    private val _appTheme = MutableStateFlow<AppTheme?>(null)
    val uiSettings = _appTheme.asStateFlow()

    private val _availableThemes = MutableStateFlow<List<AppTheme>>(AppTheme.entries)
    val availableThemes = _availableThemes.asStateFlow()

    init {
        getFontSizeUseCase().onEach {
            _fontSize.value = it
        }.launchIn(viewModelScope)

        getThemeIndexUseCase().onEach {
            _appTheme.value = _availableThemes.value[it]
        }.launchIn(viewModelScope)

    }

    fun increment() {
        viewModelScope.launch {
            val newSize = (_fontSize.value + 1f).coerceAtMost(30f)
            changeFontSizeUseCase(newSize)
        }
    }

    fun decrement() {
        viewModelScope.launch {
            val newSize = (_fontSize.value - 1f).coerceAtLeast(10f)
            changeFontSizeUseCase(newSize)
        }
    }


    fun setUiSetting(appTheme: AppTheme) {
        val index = _availableThemes.value.indexOf(appTheme)
        viewModelScope.launch(Dispatchers.IO) {
            changeThemeIndexUseCase.invoke(index)
        }
    }
}
