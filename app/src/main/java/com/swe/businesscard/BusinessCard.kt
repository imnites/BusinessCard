package com.swe.businesscard

import android.app.Application
import com.swe.businesscard.database.Database
import com.swe.businesscard.repository.UserRepository

class BusinessCard : Application() {

  val database by lazy { Database.getDatabase(this) }
  val repository by lazy { UserRepository(database.userDAO()) }
}