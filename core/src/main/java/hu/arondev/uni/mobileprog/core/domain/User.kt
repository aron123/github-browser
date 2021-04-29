package hu.arondev.uni.mobileprog.core.domain

data class User(
    var avatar_url: String = "",
    var bio: String? = "",
    var blog: String = "",
    var company: String = "",
    var created_at: String = "",
    var email: String? = "",
    var followers: Int = 0,
    var following: Int = 0,
    var hireable: Boolean = false,
    var html_url: String = "",
    var id: Int = 0,
    var location: String = "",
    var login: String = "",
    var name: String = "",
    var public_gists: Int = 0,
    var public_repos: Int = 0,
    var site_admin: Boolean = false,
    var type: String = "",
    var updated_at: String = "",
    var url: String = ""
)