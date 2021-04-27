package hu.arondev.uni.mobileprog.framework.db.dao

import okhttp3.OkHttpClient

class IssueDao(private val client: OkHttpClient) {
    suspend fun getIssuesOfRepo() {
        TODO()
    }

    suspend fun getOneIssueOfRepo() {
        TODO()
    }

    suspend fun getCommentsOfIssue() {
        TODO()
    }
}