package hu.arondev.uni.mobileprog.core.domain

data class IssueComment(
    val author_association: String = "",
    val body: String = "",
    val created_at: String = "",
    val html_url: String = "",
    val id: Int = 0,
    val issue_url: String = "",
    val node_id: String = "",
    val performed_via_github_app: Any = Any(),
    val updated_at: String = "",
    val url: String = "",
    val user: Owner = Owner()
)