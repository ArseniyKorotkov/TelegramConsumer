package by.arsy.telegramdrivemessageconsumer.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.arsy.telegramdrivemessageconsumer.MainViewModel
import by.arsy.telegramdrivemessageconsumer.R

@Composable
fun EnterScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    var text by rememberSaveable { mutableStateOf("") }
    Column(
        verticalArrangement = Arrangement.spacedBy(space = 8.dp),
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.input_bot_token),
            fontSize = 24.sp
        )
        Text(
            text = stringResource(R.string.grant_permission),
            fontSize = 12.sp
        )
        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                viewModel.startBot(token = text.trim())
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("save")
        }
    }
}