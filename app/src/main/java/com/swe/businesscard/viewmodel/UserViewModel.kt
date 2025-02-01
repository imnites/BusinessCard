package com.swe.businesscard.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swe.businesscard.database.User
import com.swe.businesscard.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

  val allUsers: LiveData<List<User>> = userRepository.getAllUsers()

  fun getUserById(userId: Int): LiveData<User> = userRepository.getUserById(userId)

  fun deleteUser(userId: Int) {
    viewModelScope.launch {
      userRepository.deleteUserById(userId)
    }
  }

  fun restoreUser(user: User) {
    viewModelScope.launch {
      userRepository.addUser(user) // Assuming addUser() method adds a user back
    }
  }

  fun addUser(user: User) {
    viewModelScope.launch {
      userRepository.addUser(user)
    }
  }

  fun updateUser(user: User) {
    viewModelScope.launch {
      userRepository.updateUser(user)
    }
  }
}