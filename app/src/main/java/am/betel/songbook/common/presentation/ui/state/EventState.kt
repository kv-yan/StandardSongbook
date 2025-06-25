package am.betel.songbook.common.presentation.ui.state

sealed class UiEvent {
    data class ShowMessage(val message: String) : UiEvent()
}
