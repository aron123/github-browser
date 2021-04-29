package hu.arondev.uni.mobileprog.core.domain

data class Owner(
    var avatar_url: String = "",
    var html_url: String = "",
    var id: Int = 0,
    var login: String = "",
    var site_admin: Boolean = false,
    var type: String = "",
    var url: String = ""
)