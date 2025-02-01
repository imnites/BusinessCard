import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Person(
  val id: Int,
  val name: String,
  val role: String,
  val company: String,
  val email: String
)


@Composable
fun UserListScreen(
  person: Person,
  onRowClick: (Person) -> Unit,
  onEditClick: () -> Unit,
  onDeleteClick: () -> Unit
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .clickable { onRowClick(person) },
    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Column(
        modifier = Modifier.weight(1f)
      ) {
        Text(
          text = person.name,
          fontSize = 18.sp,
          fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
          text = "${person.role} at ${person.company}",
          fontSize = 14.sp,
          color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
          text = person.email,
          fontSize = 14.sp,
          color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )
      }

      Row {
        IconButton(onClick = onEditClick) {
          Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = "Edit"
          )
        }
        IconButton(onClick = onDeleteClick) {
          Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "Delete"
          )
        }
      }
    }
  }
}
