package hu.arondev.uni.mobileprog.framework.db.dao

import okhttp3.OkHttpClient

class RepoDao(private val client: OkHttpClient) {
    suspend fun searchReposByName() {
        TODO()
    }

    suspend fun getRepoByFullName() {
        TODO()
    }
}