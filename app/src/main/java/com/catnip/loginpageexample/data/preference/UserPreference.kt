package com.catnip.loginpageexample.data.preference

import android.content.Context
import android.content.SharedPreferences

class UserPreference(context: Context) {
    private var preference = context.getSharedPreferences(PREF_NAME, PREF_MODE)

    companion object {
        private const val PREF_NAME = "LoginApp"
        private const val PREF_MODE = Context.MODE_PRIVATE
        private val PREF_IF_USER_LOGGED_IN = Pair("IS_USER_LOGED_IN", false)

    }

    var isUserLoggedIn: Boolean
        get() = preference.getBoolean(PREF_IF_USER_LOGGED_IN.first, PREF_IF_USER_LOGGED_IN.second)
        set(value) = preference.edit {
            it.putBoolean(PREF_IF_USER_LOGGED_IN.first, value)
        }
}

private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
    val editor = edit()
    operation(editor)
    editor.apply()
}