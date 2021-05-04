package hu.arondev.uni.mobileprog.githubbrowser

interface MainActivityDelegate {
    fun openRepositoryPage(user: String, repo: String)
    fun openUserPage(user: String)
}