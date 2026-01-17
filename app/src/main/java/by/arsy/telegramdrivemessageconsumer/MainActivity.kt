package by.arsy.telegramdrivemessageconsumer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import by.arsy.telegramdrivemessageconsumer.ui.screen.EnterScreen
import by.arsy.telegramdrivemessageconsumer.ui.screen.MainScreen
import by.arsy.telegramdrivemessageconsumer.ui.theme.TelegramDriveMessageConsumerTheme

class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TelegramDriveMessageConsumerTheme {
                val ready by mainViewModel.ready.collectAsState()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues = innerPadding)
                        .padding(8.dp)

                    if (ready) {
                        MainScreen(
                            mainViewModel = mainViewModel,
                            modifier = modifier
                        )
                    } else {
                        EnterScreen(
                            viewModel = mainViewModel,
                            modifier = modifier
                        )
                    }
                }
            }
        }
    }
}