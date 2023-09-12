package com.example.dice

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dice.ui.theme.DiceTheme
import java.util.*

class MainActivity : ComponentActivity() {
    private lateinit var resultTextView : TextView
    private lateinit var rollButton : Button
    private lateinit var diceImage: ImageView

    private val diceImages = arrayOf(
        R.drawable.dice1,
        R.drawable.dice2,
        R.drawable.dice3,
        R.drawable.dice4,
        R.drawable.dice5,
        R.drawable.dice6
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            resultTextView= findViewById(R.id.resultTextView)
            rollButton=findViewById(R.id.rollButton)
        diceImage = findViewById(R.id.diceImage)
        rollButton.setOnClickListener{
            rollDice()

        }
    }

private fun rollDice() {
    val random = Random()
    val randomNumber = random.nextInt(6) + 1
    val resultText = " The Number You rolled is  $randomNumber"
    resultTextView.text = resultText
    diceImage.setImageResource(diceImages[randomNumber - 1])
}
}
