package com.example.kaninistocks

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
        const val USER_Email = "user_email"
        const val USER_Member = "user_member"
        const val User_ID = "user_id"
    }

    fun saveAuthToken(token: String,email:String,memberSince:Long) {
        val editor = prefs.edit()
        editor.apply{
            putString(USER_TOKEN, token)
            putString(USER_Email,email)
            putLong(USER_Member,memberSince)
        }.apply()
        //editor.putString(USER_TOKEN, token)
        //editor.apply()
    }

    fun saveUserID(ID:String?=null){
        val editor=prefs.edit()
        editor.putString(User_ID,ID)
        editor.apply()
    }

    fun fetchAuthToken(): String? {

        return prefs.getString(USER_TOKEN, null)
    }
    fun fetchEmail(): String? {

        return prefs.getString(USER_Email, null)
    }
    fun fetchMemberSince(): Long? {

        return prefs.getLong(USER_Member, 0)
    }

    fun FetchUserID():String?{
        return prefs.getString(User_ID,null)
    }


}