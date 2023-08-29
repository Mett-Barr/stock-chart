package com.example.stockchart.data.settings

import android.content.Context

class SharedPrefManager(private val context: Context) {

    companion object {
        private const val PREF_NAME = "is_compose"
        private const val KEY_BOOLEAN_VALUE = "key_boolean_value"
    }

    private val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun saveBoolean(value: Boolean) {
        sharedPref.edit().putBoolean(KEY_BOOLEAN_VALUE, value).apply()
    }

    fun getBoolean(): Boolean {
        return sharedPref.getBoolean(KEY_BOOLEAN_VALUE, false)
    }
}
