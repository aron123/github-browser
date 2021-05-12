package hu.arondev.uni.mobileprog.core.domain

data class Issue(
    var id: Int = 0,
    var body: String? = "",
    var closed_at: String? = "",
    var comments: Int = 0,
    var comments_url: String = "",
    var created_at: String = "",
    var html_url: String = "",
    var locked: Boolean = false,
    var number: Int = 0,
    var repository_url: String = "",
    var state: String = "",
    var title: String = "",
    var updated_at: String = "",
    var url: String = "",
    var user: Owner = Owner()
)