package com.swe.businesscard

import HomeScreen
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import com.swe.businesscard.ui.theme.UIComponentTheme
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.swe.businesscard.ui.AddEditUserScreen
import com.swe.businesscard.ui.ProfileScreen

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      UIComponentTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
          AppNavigation()
        }
      }
    }
  }
}

@Composable
fun AppNavigation() {
  val navController = rememberNavController()

  NavHost(navController = navController, startDestination = "home") {
    composable("home") {
      HomeScreen(navController)
    }
    composable("addUser") { AddEditUserScreen(navController = navController, userId = null) }
    composable("editUser/{userId}") { backStackEntry ->
      AddEditUserScreen(
        navController = navController,
        userId = backStackEntry.arguments?.getString("userId")?.toInt()
      )
    }
    composable("detail/{personId}") { backStackEntry ->
      val personId = backStackEntry.arguments?.getString("personId")?.toInt() ?: 0
      ProfileScreen(personId)
    }
  }
}
