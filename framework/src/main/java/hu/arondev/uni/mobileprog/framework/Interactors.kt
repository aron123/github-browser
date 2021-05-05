package hu.arondev.uni.mobileprog.framework

import hu.arondev.uni.mobileprog.core.interactor.*

data class Interactors(
    val searchReposByName: SearchReposByName,
    val getOneRepositoryByFullName: GetRepositoryByFullName,
    val getReposOfUser: GetReposOfUser,
    val getFilesOfRepo: GetFilesOfRepo,
    val isRepoStarred: IsRepoStarred,
    val starRepo: StarRepo,
    val unstarRepo: UnstarRepo,
    val searchUsers: SearchUsers,
    val getCurrentUser: GetCurrentUser,
    val getOneUser: GetOneUser,
    val getIssuesOfRepo: GetIssuesOfRepo,
    val getOneIssueOfRepo: GetOneIssueOfRepo,
    val getCommentsOfIssue: GetCommentsOfIssue,
)
