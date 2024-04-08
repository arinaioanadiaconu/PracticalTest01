package ro.pub.cs.systems.eim.practicaltest01

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import ro.pub.cs.systems.eim.practicaltest01.ui.theme.PracticalTest01Theme

class MainActivity : ComponentActivity() {

    private val messageBroadcastReceiver = BroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                0)
        }

        val viewModel : MainViewModel by viewModels()
//        val viewModel = MainViewModel()

        setContent {
            PracticalTest01Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Colocviu(viewModel, this)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        val intentFilter = IntentFilter()
        Constants.actionTypes.forEach {
            intentFilter.addAction(it)
        }

        registerReceiver(messageBroadcastReceiver, intentFilter)

    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(messageBroadcastReceiver)
    }

    override fun onDestroy() {
        stopService(Intent(this, PracticalTest01Service::class.java))
        super.onDestroy()
    }
}


@Composable
fun Colocviu(viewModel: MainViewModel, context: Context) {

    LaunchedEffect(viewModel.input1, viewModel.input2) {
        if (viewModel.input1 + viewModel.input2 > Constants.THRESHOLD) {
            Log.d("PracticalTest011", "Threshold reached")
            val intent = Intent(context, PracticalTest01Service::class.java)
            intent.putExtra(Constants.INPUT1, viewModel.input1)
            intent.putExtra(Constants.INPUT2, viewModel.input2)
            context.startService(intent)
        }

    }

    val saveContactLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            Toast.makeText(
                context,
                "Result OK",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(
                context,
                "Result CANCELED",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Button(onClick = {
            val intent = Intent(context, PracticalTest01SecondaryActivity::class.java)
            intent.putExtra(Constants.INPUT1, viewModel.input1)
            intent.putExtra(Constants.INPUT2, viewModel.input2)
            saveContactLauncher.launch(intent)
        },
            modifier = Modifier
                .scale(1.2f)
                .padding(30.dp)
                ) {
            Text(text = "Navigate to secondary activity")
        }

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = viewModel.input1.toString())
            Spacer(modifier = Modifier.padding(1.dp))
            Text(text = viewModel.input2.toString())
        }

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { viewModel.input1++ }) {
                Text(text = "Press me")
            }

            Button(onClick = { viewModel.input2++ }) {
                Text(text = "Press me, too")
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            TextField(
                value = viewModel.input1.toString(),
                onValueChange = { viewModel.input1 = it.toInt() },
                label = { Text("First Value") },
                modifier = Modifier
                    .width(170.dp)
                    .padding(10.dp)

            )

            TextField(
                value = viewModel.input2.toString(),
                onValueChange = { viewModel.input2 = it.toInt() },
                label = { Text("Second Value") },
                modifier = Modifier.padding(10.dp)
            )

        }

    }
}