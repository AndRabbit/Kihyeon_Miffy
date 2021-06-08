package org.sopt.first.data

import android.content.Context

object SoptUserAuthStorage {
    private const val STORAGE_KEY = "user_auth"
    private const val USER_ID = "id"
    private const val USER_PW = "password"

    fun getUserId(context: Context): String {
        val sharedPreferences = context.getSharedPreferences(
            "${context.packageName}.$STORAGE_KEY",
            Context.MODE_PRIVATE
        )
        return sharedPreferences.getString(USER_ID, "") ?: ""
    }

    fun getUserPw(context: Context): String {
        val sharedPreferences = context.getSharedPreferences(
            "${context.packageName}.$STORAGE_KEY",
            Context.MODE_PRIVATE
        )
        return sharedPreferences.getString(USER_PW, "") ?: ""
    }

    fun saveUserId(context: Context, id: String){
        val sharedPreferences = context.getSharedPreferences(
            "${context.packageName}.$STORAGE_KEY",
            Context.MODE_PRIVATE
        )
        sharedPreferences.edit()
            .putString(USER_ID, id)
            .apply()
    }

    fun saveUserPw(context: Context, pw: String){
        val sharedPreferences = context.getSharedPreferences(
            "${context.packageName}.$STORAGE_KEY",
            Context.MODE_PRIVATE
        )
        sharedPreferences.edit()
            .putString(USER_PW, pw)
            .apply()
    }

    fun removeUSerId(context: Context) {
        val sharedPreferences = context.getSharedPreferences(
            "${context.packageName}.$STORAGE_KEY",
            Context.MODE_PRIVATE
        )
        sharedPreferences.edit()
            .remove(USER_ID)
            .apply()
    }

    fun removeUSerPw(context: Context) {
        val sharedPreferences = context.getSharedPreferences(
            "${context.packageName}.$STORAGE_KEY",
            Context.MODE_PRIVATE
        )
        sharedPreferences.edit()
            .remove(USER_PW)
            .apply()
    }

    fun clearAuthStorage(context: Context) {
        val sharedPreferences = context.getSharedPreferences(
            "${context.packageName}.$STORAGE_KEY",
            Context.MODE_PRIVATE
        )
        sharedPreferences.edit()
            .clear()
            .apply()
    }

    fun hasUserData(context: Context) : Boolean {
        val sharedPreferences = context.getSharedPreferences(
            "${context.packageName}.$STORAGE_KEY",
            Context.MODE_PRIVATE
        )
        return !sharedPreferences.getString(USER_ID, "").isNullOrBlank() &&
                !sharedPreferences.getString(USER_PW, "").isNullOrBlank()
    }
}