package com.swe.businesscard.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.swe.businesscard.repository.UserRepository

class UserViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {

  // Override create method and return the appropriate ViewModel
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
      @Suppress("UNCHECKED_CAST")
      return UserViewModel(userRepository) as T
    }
    throw IllegalArgumentException("Unknown ViewModel class")
  }

}
