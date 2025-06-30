package am.betel.share.data.usecase

import am.betel.share.domain.repository.ShareRepository
import am.betel.share.domain.usecase.ShareSongUseCase
import android.content.Context

class ShareSongUseCaseImpl(
    private val shareManager: ShareRepository,
) : ShareSongUseCase {
    override operator fun invoke(context: Context, text: String) {
        shareManager.shareText(context, text)
    }
}
