package com.swe.businesscard.database
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
  @PrimaryKey(autoGenerate = true) val id: Int = 0,
  val name: String,
  val role: String,
  val companyName: String,
  val email: String,
  val phone: String
)