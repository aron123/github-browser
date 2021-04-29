package hu.arondev.uni.mobileprog.framework.rest.entity

data class IssueEntity(
    val active_lock_reason: String = "",
    val assignee: OwnerEntity = OwnerEntity(),
    val assignees: List<OwnerEntity> = listOf(),
    val author_association: String = "",
    val body: String = "",
    val closed_at: String? = "",
    val comments: Int = 0,
    val comments_url: String = "",
    val created_at: String = "",
    val events_url: String = "",
    val html_url: String = "",
    val id: Int = 0,
    val labels_url: String = "",
    val locked: Boolean = false,
    val node_id: String = "",
    val number: Int = 0,
    val performed_via_github_app: Boolean? = null,
    val repository_url: String = "",
    val state: String = "",
    val title: String = "",
    val updated_at: String = "",
    val url: String = "",
    val user: OwnerEntity = OwnerEntity()
)