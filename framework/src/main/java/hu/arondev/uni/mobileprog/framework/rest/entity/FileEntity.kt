package hu.arondev.uni.mobileprog.framework.rest.entity

data class FileEntity(
    val download_url: String = "",
    val git_url: String = "",
    val html_url: String = "",
    val name: String = "",
    val path: String = "",
    val sha: String = "",
    val size: Int = 0,
    val type: String = "",
    val url: String = ""
)