package com.polije.dermoally_apps.utils

import android.content.Context
import android.content.Intent
import com.polije.dermoally_apps.data.prefs.UserPrefs
import com.polije.dermoally_apps.ui.view.LoginActivity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class InterceptorHeader(
    private val requestHeaders: HashMap<String, String>,
    private val userPrefs: UserPrefs,
    private val endpointsRequiringAuth: List<String>,
    private val context: Context
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url

        val response: Response

        if (requiresAuthToken(url.toString())) {
            val token = runBlocking {
                userPrefs.getSession().first().token
            }
            if (token.isNotEmpty()) {
                requestHeaders["Authorization"] = "Bearer $token"
            }

            response = chain.proceed(mapHeaders(request))

            if (response.code == 401) {
                runBlocking {
                    userPrefs.logout()
                }
                sendSessionExpiredBroadcast()
            }
        } else {
            response = chain.proceed(request)
        }

        return response
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
    private fun sendSessionExpiredBroadcast() {
        val intent = Intent("com.polije.dermoally_apps.SESSION_EXPIRED")
        context.sendBroadcast(intent)
    }

}
