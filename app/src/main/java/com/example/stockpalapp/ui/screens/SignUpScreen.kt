package com.example.stockpalapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.stockpalapp.R
import com.example.stockpalapp.model.Resource
import com.example.stockpalapp.ui.model.Routes
import com.example.stockpalapp.ui.theme.StockPalAppTheme
import com.example.stockpalapp.ui.viewmodels.AuthViewModel

@Composable
fun SignupScreen(
    viewModel: AuthViewModel?,
    navController: NavController) {

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val authResource = viewModel?.signupFlow?.collectAsState()
    val context = LocalContext.current

    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center)
    {
        Text(
            text = stringResource(R.string.signup),
            style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.size(18.dp))
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = {
                Text(text = stringResource(R.string.name))
            },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                autoCorrect = false,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            singleLine = true,
        )
        Spacer(modifier = Modifier.size(12.dp))
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            label = {
                Text(text = stringResource(id = R.string.email))
            },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            singleLine = true,
        )
        Spacer(modifier = Modifier.size(12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = {
                Text(text = stringResource(id = R.string.password))
            },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        )
        Spacer(modifier = Modifier.size(12.dp))
        Button(onClick = {viewModel?.handleSignUpClick(
            name, email, password, context)})
        {
            Text(text = "Sign up")
        }
        Spacer(modifier = Modifier.size(12.dp))
        Text(
            modifier = Modifier
                .clickable {
                    navController.navigate(Routes().login) {
                        popUpTo(Routes().signup) { inclusive = true }
                    }
                },
            text = stringResource(id = R.string.alreadyRegistered),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center)

        //Khan, B. (4. september, 2022). Firebase Authentication using MVVM with Hilt and Coroutines
        //https://www.simplifiedcoding.net/firebase-authentication-using-mvvm/

        authResource?.value?.let {
            when (it) {
                is Resource.Failure -> {
                    Toast.makeText(context,
                        stringResource(R.string.error_signing_up), Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    CircularProgressIndicator()
                }
                is Resource.Success -> {
                    LaunchedEffect(Unit) {
                        navController.navigate(Routes().home) {
                            popUpTo(Routes().signup) { inclusive = true }
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    StockPalAppTheme(useDarkTheme = false) {
        val navController = rememberNavController()
        SignupScreen(viewModel = null, navController)
    }
}


