package ro.pub.cs.systems.eim.practicaltest01

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ro.pub.cs.systems.eim.practicaltest01.ui.theme.PracticalTest01Theme

class PracticalTest01SecondaryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val input1 = intent.getIntExtra(Constants.INPUT1, 0)
        val input2 = intent.getIntExtra(Constants.INPUT2, 0)

        val sum = input1 + input2


        setContent {
            PracticalTest01Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(text = "Hello from the secondary activity!")

                        Spacer(modifier = Modifier.fillMaxWidth().height(30.dp))

                        Text(text = "The sum is: $sum")

                        Row (
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Button(onClick = {
                                setResult(Activity.RESULT_OK)
                                finish()
                            }) {
                                Text(text = "Ok")
                            }

                            Button(onClick = {
                                setResult(Activity.RESULT_CANCELED)
                                finish()
                            }) {
                                Text(text = "Cancel")
                            }
                        }
                    }
                }
            }
        }
    }
}