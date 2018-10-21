package com.shekh.test.telstra.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

/**
 * Helper Class to store and retrieve Shared Preference Data
 */
class AppPreferences {

    companion object {
        /**
         * get default shared preference of the App
         *
         * @param context application context
         */
        private fun getPreferences(context: Context): SharedPreferences {
            return PreferenceManager.getDefaultSharedPreferences(context)
        }

        /**
         * stores String value to shared preference
         *
         * @param context application context
         * @param key preference key
         * @param value to store in preference
         */
        fun savePreferences(context: Context, key: String, value: String): Boolean {
            return getPreferences(context).edit().putString(key, value).commit()
        }

        /**
         * stores Int value to shared preference
         *
         * @param context application context
         * @param key preference key
         * @param value to store in preference
         */
        fun savePreferences(context: Context, key: String, value: Int): Boolean {
            return getPreferences(context).edit().putInt(key, value).commit()
        }

        /**
         * stores Boolean value to shared preference
         *
         * @param context application context
         * @param key preference key
         * @param value to store in preference
         */
        fun savePreferences(context: Context, key: String, value: Boolean): Boolean {
            return getPreferences(context).edit().putBoolean(key, value).commit()
        }

        /**
         * retrieve String value from shared preference
         *
         * @param context application context
         * @param key preference key
         */
        fun getString(context: Context, key: String): String? {
            return getPreferences(context).getString(key, null)
        }

        /**
         * retrieve Int value from shared preference
         *
         * @param context application context
         * @param key preference key
         */
        fun getInt(context: Context, key: String): Int {
            return getPreferences(context).getInt(key, 1)
        }

        /**
         * retrieve Boolean value from shared preference
         *
         * @param context application context
         * @param key preference key
         */
        fun getBoolean(context: Context, key: String): Boolean {
            return getPreferences(context).getBoolean(key, false)
        }
    }

    object Key {
        const val TITLE_CACHE = "TITLE_CACHE"
    }
}