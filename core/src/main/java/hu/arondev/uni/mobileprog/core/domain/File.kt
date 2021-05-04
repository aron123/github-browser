package hu.arondev.uni.mobileprog.core.domain

data class File(
        var name: String = "",
        var path: String = "",
        var sha: String = "",
        var size: Int = 0,
        var type: String = "",
        var url: String = ""
): Comparable<File> {
    override fun compareTo(other: File): Int {
        if (this.type != "dir" && other.type == "dir") {
            return 1
        }

        if (this.type == "dir" && other.type != "dir") {
            return -1
        }

        return this.name.compareTo(other.name)
    }
}
