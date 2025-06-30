package am.betel.share.domain.repository

import android.content.Context

interface ShareRepository {
    fun shareText(context: Context, text: String)
}
