package hu.arondev.uni.mobileprog.framework.util

import java.text.DecimalFormat

object StringUtil {
    fun readableFileSize(size: Int): String {
        if (size <= 0) {
            return "0"
        }

        val units = arrayOf("B", "kB", "MB", "GB", "TB")
        val digitGroups = (Math.log10(size.toDouble()) / Math.log10(1024.0)).toInt()

        return DecimalFormat("#,##0.#").format(
                size / Math.pow(1024.0, digitGroups.toDouble())
        ) + " " + units[digitGroups]
    }

    fun getParentPath(path: String): String {
        if (!path.contains("/")) {
            return ""
        }

        val pathElements = path.split("/")
        if (pathElements.size <= 1) {
            return ""
        }

        val parentPathElements = pathElements.subList(0, pathElements.lastIndex)
        return parentPathElements.joinToString("/")
    }
}