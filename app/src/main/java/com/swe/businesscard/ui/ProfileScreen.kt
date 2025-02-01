package com.swe.businesscard.ui

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel

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