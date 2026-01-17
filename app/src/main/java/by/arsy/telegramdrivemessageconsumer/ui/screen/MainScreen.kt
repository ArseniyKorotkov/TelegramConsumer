package by.arsy.telegramdrivemessageconsumer.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import by.arsy.telegramdrivemessageconsumer.MainViewModel

@Composable
fun MainScreen(
    mainViewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val currentMessage by mainViewModel.message.collectAsState()
    Box(modifier = modifier) {
        Text(
            text = "Message is:\n$currentMessage"
        )
    }
}