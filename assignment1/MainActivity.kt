package com.example.loginpage
import com.example.loginpage.ui.theme.LoginpageTheme
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginpageTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colorScheme.background) {
                    MyLoginScreen()
                }
            }
        }
    }

    fun checkLogin(username: String, password: String){
        // Call API get response  from API
        // do whatever you want on response
        if(username.equals("Ashritha") && password.equals("21bce8697")){
            Toast.makeText(this, "Logged in successful", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show()
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MyLoginScreen() {

        var username = remember { mutableStateOf("")}
        var password = remember { mutableStateOf("")}

        Box (
            modifier = Modifier.background(Color.White)
        ){
            Column(
                modifier = Modifier.fillMaxSize().padding(20.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(text = "LOGIN PAGE", color = Color.Black,fontSize = 30.sp)
                Spacer(modifier = Modifier.height(20.dp))
            }
            Column(
                modifier = Modifier.fillMaxSize().padding(20.dp),
                verticalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    value = username.value,
                    onValueChange = {
                        username.value = it
                    },
                    label = {  // Floating label top of input box
                        Text(text = "User name")
                    },
                    placeholder = {   // hint
                        Text(text = "Enter user name")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = password.value,
                    onValueChange = {
                        password.value = it
                    },
                    label = {
                        Text(text = "Password")
                    },
                    placeholder = {
                        Text(text = "Enter Password")
                    },
                    modifier = Modifier.fillMaxWidth()

                )
            }
            Column(
                modifier = Modifier.fillMaxSize().padding(20.dp),
                verticalArrangement = Arrangement.Bottom
            ) {

                OutlinedButton(
                    onClick = {  // onclick listner
                        checkLogin(username.value, password.value )
                    },
                    modifier = Modifier.fillMaxWidth() ) {
                    Text(text = "Login")
                }
            }
        }


    }


    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        LoginpageTheme {
            MyLoginScreen()
        }
    }
}