package hu.arondev.uni.mobileprog.framework.rest

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request

object AuthClient {

    private var instance: OkHttpClient? = null

    private fun create(authToken: String): OkHttpClient {
        val clientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()

        clientBuilder.addInterceptor(Interceptor() { chain ->
            val original: Request = chain.request()

            val request: Request = original.newBuilder()
                .header("Accept", "application/vnd.github.v3+json")
                .header("Authorization", "Basic $authToken")
                .method(original.method, original.body)
                .build()

            chain.proceed(request)
        })

        return clientBuilder.build()
    }

    fun getInstance(authToken: String): OkHttpClient = instance ?: create(authToken).also{ instance = it }
}