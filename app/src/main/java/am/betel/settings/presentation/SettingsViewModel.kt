package am.betel.settings.presentation

import am.betel.settings.domain.usecase.ChangeFontSizeUseCase
import am.betel.settings.domain.usecase.GetFontSizeUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SettingsViewModel(
    getFontSizeUseCase: GetFontSizeUseCase,
    private val changeFontSizeUseCase: ChangeFontSizeUseCase,
) : ViewModel() {

    private val _fontSize = MutableStateFlow(16f)
    val fontSize: StateFlow<Float> = _fontSize

    init {
        getFontSizeUseCase().onEach {
            _fontSize.value = it
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
}
