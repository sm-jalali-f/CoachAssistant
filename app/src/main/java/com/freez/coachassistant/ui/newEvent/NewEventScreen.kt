package com.freez.coachassistant.ui.newEvent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.freez.coachassistant.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewClassEventScreen(
    onClose: () -> Unit,
    onSave: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("New class Event") },
                navigationIcon = {
                    IconButton(onClick = onClose) {
                        Icon(
                            painter = painterResource(R.drawable.ic_add),
//                            imageVector = Icons.Default.Close,
                            contentDescription = "Close"
                        )
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            TimeSection()

            PlayersSection()

            ClubSection()

            CostSection()

            BallBoySection()

            StatusSection()

            ReserveWeeksSection()

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onSave
            ) {
                Text("Save")
            }
        }
    }
}
@Composable
private fun TimeSection() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Start: 9:00 PM    Wed, 7 Jan 2026")
        Text("End:   10:00 PM")
    }
}
@Composable
private fun PlayersSection() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Players")

        OutlinedCard {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_add),
                    contentDescription = null
                )
                Spacer(Modifier.width(8.dp))
                Text("Alex")
            }
        }

        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { /* select other player */ }
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_add),
//                Icons.Default.Add,
                contentDescription = null)
            Spacer(Modifier.width(8.dp))
            Text("Select other player")
        }
    }
}
@Composable
private fun ClubSection() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Club")

        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { /* select club */ }
        ) {
            Text("Select club")
        }
    }
}
@Composable
private fun CostSection() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

        CostRow(
            label = "Club cost",
            value = "1000"
        )

        CostRow(
            label = "Teach cost",
            value = ""
        )
    }
}

@Composable
private fun CostRow(
    label: String,
    value: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = label
        )

        OutlinedTextField(
            modifier = Modifier.weight(1f),
            value = value,
            onValueChange = {},
            singleLine = true
        )

        Spacer(Modifier.width(8.dp))

        Text("$")
    }
}
@Composable
private fun BallBoySection() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Ball Boy Player")

        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { /* select person */ }
        ) {
            Text("Select Person")
        }

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = "",
            onValueChange = {},
            label = { Text("Ball Boy cost") },
            singleLine = true
        )
    }
}
@Composable
private fun StatusSection() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Status")

        OutlinedButton(onClick = { /* change status */ }) {
            Text("برگزار شد")
        }
    }
}
@Composable
private fun ReserveWeeksSection() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Reserve for")

        Spacer(Modifier.width(8.dp))

        OutlinedTextField(
            modifier = Modifier.width(80.dp),
            value = "",
            onValueChange = {},
            singleLine = true
        )

        Spacer(Modifier.width(8.dp))

        Text("weeks")
    }
}
