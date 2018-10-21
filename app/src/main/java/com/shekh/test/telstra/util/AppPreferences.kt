package com.shekh.test.telstra.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

/**
 * Helper Class to store and retrieve Shared Preference Data
 */
class AppPreferences {

    companion object {
        private fun getPreferences(context: Context): SharedPreferences {
            return PreferenceManager.getDefaultSharedPreferences(context)
        }

        fun savePreferences(context: Context, key: String, value: String): Boolean {
            return getPreferences(context).edit().putString(key, value).commit()
        }

        fun savePreferences(context: Context, key: String, value: Int): Boolean {
            return getPreferences(context).edit().putInt(key, value).commit()
        }

        fun savePreferences(context: Context, key: String, value: Long): Boolean {
            return getPreferences(context).edit().putLong(key, value).commit()
        }

        fun savePreferences(context: Context, key: String, value: Boolean): Boolean {
            return getPreferences(context).edit().putBoolean(key, value).commit()
        }

        fun getString(context: Context, key: String): String? {
            return getPreferences(context).getString(key, null)
        }

        fun getInt(context: Context, key: String): Int {
            return getPreferences(context).getInt(key, 1)
        }

        fun getLong(context: Context, key: String): Long {
            return getPreferences(context).getLong(key, 1)
        }

        fun getBoolean(context: Context, key: String): Boolean {
            return getPreferences(context).getBoolean(key, false)
        }
    }

    object Key {
        const val TITLE_CACHE = "TITLE_CACHE"
    }
}