package com.swe.businesscard.ui

import com.swe.businesscard.R
import com.swe.businesscard.viewmodel.UserViewModel
import com.swe.businesscard.viewmodel.UserViewModelFactory
import com.swe.businesscard.BusinessCard
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Email
import androidx.compose.ui.graphics.Color

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
  } else {
    val user = userState.value
    Box(
      modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
    ) {
      Column(
        modifier = Modifier
          .align(Alignment.Center)
          .padding(16.dp, 0.dp, 16.dp, 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
      ) {
        Image(
          painter = painterResource(id = R.drawable.android_logo),
          contentDescription = "Logo",
          modifier = Modifier
            .size(160.dp)
            .background(Color(0xFF006400)) // Dark Green background
            .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
          text = user?.name ?: "",
          fontSize = 32.sp,
          fontWeight = FontWeight.Normal
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
          text = "${user?.role} at ${user?.companyName}",
          fontSize = 18.sp,
          color = Color(0xFF006400), // Dark Green
          fontWeight = FontWeight.Medium
        )
      }

      Column(
        modifier = Modifier
          .align(Alignment.BottomCenter)
          .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Bottom
      ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
          Icon(
            imageVector = Icons.Filled.Phone,
            contentDescription = "Phone",
            modifier = Modifier.size(20.dp),
            tint = Color(0xFF006400) // Dark Green
          )
          Spacer(modifier = Modifier.width(16.dp))
          Text(
            text = user?.phone ?: "",
            fontSize = 16.sp
          )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
          Icon(
            imageVector = Icons.Filled.Share,
            contentDescription = "Share LinkedIn",
            modifier = Modifier.size(20.dp),
            tint = Color(0xFF006400)
          )
          Spacer(modifier = Modifier.width(16.dp))
          Text(
            text = user?.linkedin ?: "",
            fontSize = 16.sp
          )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
          Icon(
            imageVector = Icons.Filled.Email,
            contentDescription = "Email",
            modifier = Modifier.size(20.dp),
            tint = Color(0xFF006400)
          )
          Spacer(modifier = Modifier.width(16.dp))
          Text(
            text = user?.email ?: "",
            fontSize = 16.sp
          )
        }
      }
    }
  }
}
