package am.betel.share.data.repository

import am.betel.share.domain.repository.ShareRepository
import android.content.Context
import android.content.Intent

class ShareRepositoryImpl: ShareRepository {
    override fun shareText(context: Context, text: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, text)
        }
        context.startActivity(Intent.createChooser(intent, "Share song lyrics"))
    }
}
