package com.example.kaninistocks

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: User)


    @Query("DELETE FROM users WHERE email=:email")
    fun deleteuserAccount(email: String)


    @Query("UPDATE USERS Set email=:email")
    fun updateuserAccount(email: String)

    @Query("SELECT * FROM users ORDER BY email DESC")
    fun getAllUsers(): LiveData<List<User>>

    @Query("SELECT * FROM users WHERE email LIKE :email")
    suspend fun getUsername(email: String): List<User>?=null

}