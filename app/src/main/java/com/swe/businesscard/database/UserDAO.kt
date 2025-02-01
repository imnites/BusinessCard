package com.swe.businesscard.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.swe.businesscard.database.User

@Dao
interface UserDAO {
  @Insert
  suspend fun insert(user: User)

  @Update
  suspend fun updateUser(user: User)

  @Query("SELECT * FROM users")
  fun getAllUsers(): LiveData<List<User>>

  @Query("SELECT * FROM users WHERE id = :userId")
  fun getUserById(userId: Int): LiveData<User>

  @Query("DELETE FROM users WHERE id = :userId")
  suspend fun deleteUserById(userId: Int)

  @Query("DELETE FROM users")
  suspend fun deleteAllUsers()
}