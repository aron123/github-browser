package hu.arondev.uni.mobileprog.framework.util

import android.text.format.DateUtils
import java.time.OffsetDateTime

object AgoFormatter {
    fun stringDateToAgoString(isoDateString: String): CharSequence {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.O) {
            return ""
        }

        val dateTime = OffsetDateTime.parse(isoDateString)
        val epochDateTime = dateTime.toInstant().toEpochMilli()
        return DateUtils.getRelativeTimeSpanString(
                epochDateTime, System.currentTimeMillis(),
                DateUtils.DAY_IN_MILLIS, DateUtils.FORMAT_ABBREV_RELATIVE
        )
    }
}