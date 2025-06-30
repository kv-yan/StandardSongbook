package am.betel.share.domain.usecase

import android.content.Context


interface ShareSongUseCase {
    operator fun invoke(context: Context, text: String)
}