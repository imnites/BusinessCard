import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.swe.businesscard.BusinessCard
import com.swe.businesscard.viewmodel.UserViewModel
import com.swe.businesscard.viewmodel.UserViewModelFactory
import kotlinx.coroutines.launch
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment

@Composable
fun HomeScreen(navController: NavHostController) {
  // Use SnackbarHostState for Material 3
  val snackbarHostState = remember { SnackbarHostState() }
  val coroutineScope = rememberCoroutineScope()

  val context = LocalContext.current
  val userRepository = (context.applicationContext as BusinessCard).repository

  val userViewModel: UserViewModel = viewModel(
    factory = UserViewModelFactory(userRepository)
  )

  val users = userViewModel.allUsers.observeAsState(emptyList())

  // Custom Scaffold-like layout
  Box(modifier = Modifier.fillMaxSize()) {
    // Content
    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 80.dp)
    ) {
      if (users.value.isEmpty()) {
        item {
          Text(
            "No users available",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
          )
        }
      } else {
        items(users.value, key = { it.id }) { u ->
          PersonListItem(
            person = Person(u.id, u.name, u.role, u.companyName, u.email),
            onEditClick = {
              navController.navigate("editUser/${u.id}")
            },
            onDeleteClick = {
              userViewModel.deleteUser(u.id)

              // Show Snackbar after deletion
              coroutineScope.launch {
                val result = snackbarHostState.showSnackbar(
                  message = "User deleted successfully",
                  actionLabel = "Undo"
                )
                // Handle undo if clicked
                if (result == SnackbarResult.ActionPerformed) {
                  userViewModel.restoreUser(u)
                }
              }
            },
            onRowClick = {
              navController.navigate("detail/${u.id}")
            }
          )
          Spacer(modifier = Modifier.height(8.dp))
        }
      }
    }

    SnackbarHost(
      hostState = snackbarHostState,
      modifier = Modifier.align(Alignment.BottomCenter)
    )

    FloatingActionButton(
      onClick = {
        navController.navigate("addUser")
      },
      modifier = Modifier
        .align(Alignment.BottomEnd)
        .padding(32.dp)
    ) {
      Icon(
        imageVector = Icons.Default.Add,
        contentDescription = "Add Business Card"
      )
    }
  }
}