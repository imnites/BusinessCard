package com.swe.businesscard.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.swe.businesscard.R
import com.swe.businesscard.viewmodel.UserViewModel
import com.swe.businesscard.viewmodel.UserViewModelFactory
import com.swe.businesscard.BusinessCard
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.swe.businesscard.ui.theme.UIComponentTheme
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel

//class DetailActivity : AppCompatActivity() {
//
//  // Use the factory to create the ViewModel
//  private val userViewModel: UserViewModel by viewModels {
//    UserViewModelFactory((application as BusinessCard).repository)
//  }
//
//  override fun onCreate(savedInstanceState: Bundle?) {
//    super.onCreate(savedInstanceState)
//
//    val userId = intent.getIntExtra("USER_ID", -1)
//
//    if (userId != -1) {
//      userViewModel.getUserById(userId).observe(this) { user ->
//        setContent {
//          UIComponentTheme {
//            Surface(
//              modifier = Modifier.fillMaxSize(),
//              color = MaterialTheme.colorScheme.background
//            ) {
//              ProfileScreen(
//                logo = painterResource(id = R.drawable.ic_launcher_foreground), // Replace with your logo resource
//                name = user.name,
//                role = user.role,
//                phoneNumber = user.phone,
//                socialMedia = "@johndoe",
//                email = "johndoe@example.com"
//              )
//            }
//          }
//        }
//      }
//    }
//  }
//}

@Composable
fun ProfileScreen(
  id: Int
) {

  val context = LocalContext.current
  val userRepository = (context.applicationContext as BusinessCard).repository

  val userViewModel: UserViewModel = viewModel(
    factory = UserViewModelFactory(userRepository)
  )

  val userState = userViewModel.getUserById(id).observeAsState()

  if (userState.value == null) {
    Text(text = "Loading user data...", modifier = Modifier.padding(16.dp))
  }else {
    val user = userState.value
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      // Logo
      Image(
        painter = painterResource(id = R.drawable.android_logo),
        contentDescription = "Logo",
        modifier = Modifier.size(120.dp)
      )

      Spacer(modifier = Modifier.height(16.dp))

      // Name
      Text(
        text = user?.name?:"",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
      )

      Spacer(modifier = Modifier.height(8.dp))

      // Role
      Text(
        text = user?.role?:"",
        fontSize = 18.sp,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
      )

      Spacer(modifier = Modifier.height(32.dp))

      // Contact Information
      Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
      ) {
        Text(
          text = user?.phone?:"",
          fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
          text = user?.email?:"",
          fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
          text = user?.email?:"",
          fontSize = 16.sp
        )
      }
    }
  }
}

//@Preview(showBackground = true)
//@Composable
//fun DetailActivityPreview() {
//  UIComponentTheme {
//    ProfileScreen(
//      logo = painterResource(id = R.drawable.ic_launcher_foreground), // Replace with your logo resource
//      name = "John Doe",
//      role = "Software Engineer",
//      phoneNumber = "+1 234 567 890",
//      socialMedia = "@johndoe",
//      email = "johndoe@example.com"
//    )
//  }
//}