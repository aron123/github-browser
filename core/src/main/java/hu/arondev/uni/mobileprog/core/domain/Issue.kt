package hu.arondev.uni.mobileprog.core.domain

data class Issue(
    val active_lock_reason: Any = Any(),
    val assignee: Any = Any(),
    val assignees: List<Any> = listOf(),
    val author_association: String = "",
    val body: String = "",
    val closed_at: Any = Any(),
    val comments: Int = 0,
    val comments_url: String = "",
    val created_at: String = "",
    val events_url: String = "",
    val html_url: String = "",
    val id: Int = 0,
    val labels: List<Any> = listOf(),
    val labels_url: String = "",
    val locked: Boolean = false,
    val milestone: Any = Any(),
    val node_id: String = "",
    val number: Int = 0,
    val performed_via_github_app: Any = Any(),
    val repository_url: String = "",
    val state: String = "",
    val title: String = "",
    val updated_at: String = "",
    val url: String = "",
    val user: Owner = Owner()
)