@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.login

import  android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.content.ActivityNotFoundException
import android.content.Context
import android.support.v4.os.IResultReceiver.Default
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.login.ui.theme.LoginTheme
import android.content.pm.PackageManager
import android.graphics.drawable.Icon
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen1()
        }
    }
}

@Composable
fun LoginScreen1() {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var selectedWebsite by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    val context = LocalContext.current


    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "LOGIN",
            style = MaterialTheme.typography.headlineLarge.copy(color = Color.Black)
        )
        Spacer(modifier = Modifier.height(32.dp))
        Row {
            OutlinedTextField(
                value = username,
                onValueChange = { username = it},
                placeholder = {Text("Username",color = Color.Black)},
                textStyle = TextStyle(color = Color.Black),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.img_1),
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.size(24.dp)
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Row {
            OutlinedTextField(
                value = password,
                onValueChange = { password = it},
                placeholder = {Text("Password",color = Color.Black)},
                textStyle = TextStyle(color = Color.Black),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.img_2),
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.size(24.dp)
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))


        RadioButtonGroup(
            selectedWebsite = selectedWebsite,
            onWebsiteSelected = { selectedWebsite = it }
        )


        Spacer(modifier = Modifier.height(16.dp))

        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Button(
            onClick = {
                if (selectedWebsite.isNotEmpty() && username.isNotEmpty() && password.isNotEmpty()) {
                    val randomUrl = generateRandomUrl(selectedWebsite)
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(randomUrl))
                    context.startActivity(intent)
                } else {
                    errorMessage = "Please fill in all fields."
                }
            }
        ) {
            Text(text="Login", color= Color.Black)

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen1()
}

@Composable
fun RadioButtonGroup(
    selectedWebsite: String,
    onWebsiteSelected: (String) -> Unit
) {
    val websites = listOf(
        WebsiteOption("pintrest", R.drawable.pintrest),
        WebsiteOption("Youtube", R.drawable.youtube),
        WebsiteOption("Instagram", R.drawable.instagram),
        WebsiteOption("Amazon", R.drawable.amazon)
    )

    websites.forEach { option ->
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = selectedWebsite == option.websiteName,
                onClick = { onWebsiteSelected(option.websiteName) }
            )
            Icon(
                painter = painterResource(id = option.iconResourceId),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(24.dp)
            )
            Text(option.websiteName,color = Color.Black, modifier = Modifier
                .padding(start = 8.dp))
        }
    }
}
data class WebsiteOption(val websiteName: String, val iconResourceId: Int)
fun generateRandomUrl(website: String): String {
    val websiteUrls = mapOf(
        "pintrest" to "https://in.pinterest.com/",
        "Youtube" to "https://www.youtube.com",
        "Instagram" to "https://www.instagram.com",
        "Amazon" to "https://www.amazon.com"
    )
    return websiteUrls[website] ?: ""
}