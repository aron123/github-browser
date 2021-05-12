package hu.arondev.uni.mobileprog.core.domain

data class IssueComment(
    var author_association: String = "",
    var body: String? = "",
    var created_at: String = "",
    var html_url: String = "",
    var id: Int = 0,
    var issue_url: String = "",
    var updated_at: String = "",
    var url: String = "",
    var user: Owner = Owner()
)