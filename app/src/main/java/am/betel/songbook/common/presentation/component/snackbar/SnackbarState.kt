package am.betel.songbook.common.presentation.component.snackbar

import am.betel.songbook.R


sealed class SnackbarState(val message: Int, val icon: Int? = null) {
    data class Error(
        val _message: Int = R.string.empty_string,
        val _icon: Int = R.drawable.ic_error,
    ) : SnackbarState(message = _message, icon = _icon)

    data class Success(
        val _message: Int = R.string.empty_string,
        val _icon: Int? = null,
    ) : SnackbarState(message = _message, icon = _icon)
}