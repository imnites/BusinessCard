package com.swe.businesscard.repository

import androidx.lifecycle.LiveData
import com.swe.businesscard.database.User
import com.swe.businesscard.database.UserDAO

class UserRepository(private val userDao: UserDAO) {

  fun getAllUsers(): LiveData<List<User>> = userDao.getAllUsers()

  fun getUserById(userId: Int): LiveData<User> = userDao.getUserById(userId)

  suspend fun deleteUserById(userId: Int) {
    userDao.deleteUserById(userId)
  }

  suspend fun addUser(user: User) {
    userDao.insert(user)
  }

  suspend fun updateUser(user: User) {
    userDao.updateUser(user)
  }
}
