package com.polije.dermoally_apps.utils

import com.polije.dermoally_apps.data.prefs.UserPrefs
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class InterceptorHeader(
    private val requestHeaders: HashMap<String, String>,
    private val userPrefs: UserPrefs,
    private val endpointsRequiringAuth: List<String>
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url

        if (requiresAuthToken(url.toString())) {
            val token = runBlocking {
                userPrefs.getSession().first().token
            }
            if (token.isNotEmpty()) {
                requestHeaders["Authorization"] = "Bearer $token"
            }
        }

        val modifiedRequest = mapHeaders(request)

        return chain.proceed(modifiedRequest)
    }

    private fun requiresAuthToken(url: String): Boolean {
        return endpointsRequiringAuth.any { url.contains(it) }
    }

    private fun mapHeaders(request: Request): Request {
        val requestBuilder = request.newBuilder()

        for ((key, value) in requestHeaders) {
            requestBuilder.addHeader(key, value)
        }
        return requestBuilder.build()
    }

}
