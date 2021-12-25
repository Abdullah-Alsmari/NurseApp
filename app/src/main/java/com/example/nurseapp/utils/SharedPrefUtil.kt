package com.example.nurseapp.utils

import android.content.Context

const val GENERAL_PREF = "nurse_app_general_prefs"
const val PREFS_PRIVATE_MODE = 0


fun setPrefsString(context: Context, key: String, value: String) {
    context.getSharedPreferences(GENERAL_PREF, PREFS_PRIVATE_MODE).edit()
        .putString(key, value).apply()
}

fun getStringFromPrefs(context: Context, string: String): String? {
    val sharedPreferences = context.getSharedPreferences(GENERAL_PREF, PREFS_PRIVATE_MODE)
    return sharedPreferences.getString(string, "")
}

fun getBooleanFromPrefs(context: Context, string: String): Boolean {
    val sharedPreferences = context.getSharedPreferences(GENERAL_PREF, PREFS_PRIVATE_MODE)
    return sharedPreferences.getBoolean(string, false)
}

fun setPrefsBoolean(context: Context, key: String, value: Boolean) {
    context.getSharedPreferences(GENERAL_PREF, PREFS_PRIVATE_MODE).edit()
        .putBoolean(key, value).apply()
}
