package hu.arondev.uni.mobileprog.githubbrowser

interface MainActivityDelegate {
    fun openRepositoryPage(user: String, repo: String)
    fun openUserPage(user: String)
    fun hideKeyboard()
    fun openIssueBrowsePage(user: String, repo: String)
    fun openIssuePage(user: String, repo: String, issueNumber: Int)
    fun openAddIssuePage(user: String, repo: String)
    fun openAddIssueCommentPage(user: String, repo: String, issueNumber: Int)
}