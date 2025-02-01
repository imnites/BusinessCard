package com.swe.businesscard.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.swe.businesscard.R
import com.swe.businesscard.database.Database
import com.swe.businesscard.database.UserDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.material3.Button
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.swe.businesscard.ui.theme.UIComponentTheme
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface


class AddBusinessCardActivity : AppCompatActivity() {

    private lateinit var db: Database
    private lateinit var user: UserDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            UIComponentTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AddBusinessCardScreen(
                        onSaveClick = {
                            onSaveClick()
                        }
                    )
                }
            }
        }
    }

    private fun onSaveClick()
    {
        val name = findViewById<EditText>(R.id.activity_add_business_card_name).text.toString()
        val role = findViewById<EditText>(R.id.activity_add_business_card_role).text.toString()
        val companyName = findViewById<EditText>(R.id.activity_add_business_card_company_name).text.toString()
        val email = findViewById<EditText>(R.id.activity_add_business_card_email).text.toString()
        val phone = findViewById<EditText>(R.id.activity_add_business_card_phone).text.toString()

        if (name.isNotEmpty() && role.isNotEmpty() && companyName.isNotEmpty() && email.isNotEmpty() && phone.isNotEmpty())
        {
            lifecycleScope.launch {
                save(com.swe.businesscard.database.User(name = name, role = role, companyName = companyName, email = email, phone = phone))
            }
        }
        else {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private suspend fun save(u: com.swe.businesscard.database.User)
    {
        withContext(Dispatchers.IO) {
            user.insert(u)
        }

        runOnUiThread {
            Toast.makeText(this@AddBusinessCardActivity, "User saved successfully", Toast.LENGTH_SHORT).show()
        }
    }
}

@Composable
fun AddBusinessCardScreen(
    onSaveClick: () -> Unit
) {
    // These are state variables to store user input for each field
    var name by remember { mutableStateOf("") }
    var role by remember { mutableStateOf("") }
    var companyName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    // Handle keyboard controller
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()), // Make it scrollable if needed
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Name input field
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            )
        )

        // Role input field
        OutlinedTextField(
            value = role,
            onValueChange = { role = it },
            label = { Text("Role") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            )
        )

        // Company Name input field
        OutlinedTextField(
            value = companyName,
            onValueChange = { companyName = it },
            label = { Text("Company Name") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            )
        )

        // Email input field
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            )
        )

        // Phone input field
        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Phone") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Phone
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            )
        )

        // Save button
        Button(
            onClick = onSaveClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddBusinessCardPreview() {
    UIComponentTheme {
        // Display the preview of the AddBusinessCardScreen composable
        AddBusinessCardScreen(
            onSaveClick = {}
        )
    }
}
