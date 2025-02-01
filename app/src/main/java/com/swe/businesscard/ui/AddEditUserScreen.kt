package com.swe.businesscard.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.swe.businesscard.BusinessCard
import com.swe.businesscard.database.User
import com.swe.businesscard.viewmodel.UserViewModel
import com.swe.businesscard.viewmodel.UserViewModelFactory


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditUserScreen(
  userId: Int? = null,
  navController: NavHostController,
  userViewModel: UserViewModel = viewModel(factory = UserViewModelFactory((LocalContext.current.applicationContext as BusinessCard).repository))
) {
  val name = remember { mutableStateOf("") }
  val role = remember { mutableStateOf("") }
  val companyName = remember { mutableStateOf("") }
  val email = remember { mutableStateOf("") }
  val phone = remember { mutableStateOf("") }

  userId?.let {
    val user = userViewModel.getUserById(it).observeAsState().value
    user?.let {
      name.value = user.name
      role.value = user.role
      companyName.value = user.companyName
      email.value = user.email
      phone.value= user.phone
    }
  }

  val onSaveClick = {
    val updatedUser = User(
      id = userId ?: 0,
      name = name.value,
      role = role.value,
      companyName = companyName.value,
      email = email.value,
      phone = phone.value
    )

    if (userId == null) {
      userViewModel.addUser(updatedUser)
    } else {
      userViewModel.updateUser(updatedUser)
    }

    navController.popBackStack()
  }

  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text(if (userId == null) "Add User" else "Edit User") }
      )
    },
    floatingActionButton = {
      FloatingActionButton(onClick = {
        onSaveClick()
      }) {
        Icon(Icons.Default.Add, contentDescription = "Save User")
      }
    }
  ) { paddingValues ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues)
        .padding(16.dp)
    ) {

      TextField(
        value = name.value,
        onValueChange = { name.value = it },
        label = { Text("Name") },
        modifier = Modifier.fillMaxWidth()
      )
      Spacer(modifier = Modifier.height(8.dp))
      TextField(
        value = role.value,
        onValueChange = { role.value = it },
        label = { Text("Role") },
        modifier = Modifier.fillMaxWidth()
      )
      Spacer(modifier = Modifier.height(8.dp))
      TextField(
        value = companyName.value,
        onValueChange = { companyName.value = it },
        label = { Text("Company Name") },
        modifier = Modifier.fillMaxWidth()
      )
      Spacer(modifier = Modifier.height(8.dp))
      TextField(
        value = email.value,
        onValueChange = { email.value = it },
        label = { Text("Email") },
        modifier = Modifier.fillMaxWidth()
      )
    }
  }
}
