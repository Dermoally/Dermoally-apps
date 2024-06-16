package com.polije.dermoally_apps.utils

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.polije.dermoally_apps.BuildConfig
import com.polije.dermoally_apps.data.injection.dataStoreModule
import com.polije.dermoally_apps.data.injection.networkModule
import com.polije.dermoally_apps.data.injection.repositoryModule
import com.polije.dermoally_apps.data.injection.viewModelModule
import java.text.SimpleDateFormat
import java.util.Locale


val koinModules = listOf(
    dataStoreModule,
    networkModule,
    repositoryModule,
    viewModelModule,
)

const val PREFS_NAME: String = "auth_pref"
const val TOKEN_KEY: String = "auth_token"
const val NAME_KEY: String = "auth_name"

//punya fatiya
const val API_URL = "http://192.168.100.92:9000/"

//punya fawa
//const val API_URL = "http://192.168.46.87:9000/"

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}
inline fun <reified T> startNewActivity(context: Context, finishCurrent: Boolean = false) {
    val intent = Intent(context, T::class.java)
    context.startActivity(intent)
    if (finishCurrent && context is AppCompatActivity) {
        context.finish()
    }
}

fun formatDateString(inputDate: String, inputFormat: String, outputFormat: String): String {
    return try {
        val sdf = SimpleDateFormat(inputFormat, Locale.ENGLISH)
        val date = sdf.parse(inputDate)
        val outputSdf = SimpleDateFormat(outputFormat, Locale.ENGLISH)
        outputSdf.format(date)
    } catch (e: Exception) {
        e.printStackTrace()
        inputDate
    }
}
