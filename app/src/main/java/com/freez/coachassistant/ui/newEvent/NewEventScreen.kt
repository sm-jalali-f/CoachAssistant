package com.freez.coachassistant.ui.newEvent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.freez.coachassistant.R
import com.freez.domain.model.AppDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewClassEventScreen(
    onClose: () -> Unit,
    onSave: () -> Unit,
    viewModel: NewEventViewModel
) {
    val state = viewModel.state.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(R.string.new_event_class)) }, navigationIcon = {
                IconButton(onClick = onClose) {
                    Icon(
                        painter = painterResource(R.drawable.ic_add),
                        contentDescription = "New Event Icon"
                    )
                }
            })
        }) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            TimeSection(
                state.value.tennisEvent.startDateTime,
                state.value.tennisEvent.endDateTime,
                {},
                {})
            HorizontalDivider(thickness = 1.dp)
            PlayersSection()
            HorizontalDivider(thickness = 1.dp)
            ClubSection()
            HorizontalDivider(thickness = 1.dp)
            CostSection()
            HorizontalDivider(thickness = 1.dp)
            BallBoySection()
            HorizontalDivider(thickness = 1.dp)
            StatusSection()
            HorizontalDivider(thickness = 1.dp)
            ReserveWeeksSection()
            Button(
                modifier = Modifier.fillMaxWidth(), onClick = onSave
            ) {
                Text(stringResource(R.string.save))
            }
        }
    }
}

@Composable
private fun TimeSection(
    startDateTime: AppDateTime,
    endDateTime: AppDateTime,
    onStartChange: (AppDateTime) -> Unit,
    onEndChange: (AppDateTime) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center) {
        Text(
            "${startDateTime.appDate.dayOfWeek}," +
                    " ${startDateTime.appDate.day}" +
                    " ${startDateTime.appDate.monthName}" +
                    " ${startDateTime.appDate.year}",
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "%s%02d:%02d".format(
                    stringResource(R.string.start_colon),
                    startDateTime.hour.value,
                    startDateTime.minute.value
                )
            )

            Text(
                "%s%02d:%02d".format(
                    stringResource(R.string.end_colon),
                    startDateTime.hour.value,
                    startDateTime.minute.value
                )
            )
        }
    }
    /*Row(horizontalArrangement = Arrangement.SpaceBetween) {

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("${startDateTime.appDate.dayOfWeek}, ${startDateTime.appDate.day} ${startDateTime.appDate.monthName} ${startDateTime.appDate.year}")
            Text("%02d:%02d".format(startDateTime.hour.value, startDateTime.minute.value))
        }

        Icon(
            painter = painterResource(R.drawable.outline_arrow_right_alt_24),
            contentDescription = "arrow icon"
        )

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("${endDateTime.appDate.dayOfWeek}, ${endDateTime.appDate.day} ${endDateTime.appDate.monthName} ${endDateTime.appDate.year}")
            Text("%02d:%02d".format(endDateTime.hour.value, endDateTime.minute.value))
        }
    }*/

}

@Composable
private fun PlayersSection() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(stringResource(R.string.players))

        OutlinedCard {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_add), contentDescription = null
                )
                Spacer(Modifier.width(8.dp))
                Text("Alex")
            }
        }

        OutlinedButton(
            modifier = Modifier.fillMaxWidth(), onClick = { /* select other player */ }) {
            Icon(
                painter = painterResource(R.drawable.ic_add),
//                Icons.Default.Add,
                contentDescription = null
            )
            Spacer(Modifier.width(8.dp))
            Text("Select other player")
        }
    }
}

@Composable
private fun ClubSection() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(stringResource(R.string.club))

        OutlinedButton(
            modifier = Modifier.fillMaxWidth(), onClick = { /* select club */ }) {
            Text(stringResource(R.string.select_club))
        }
    }
}

@Composable
private fun CostSection() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

        CostRow(
            label = "Club cost", value = "1000"
        )

        CostRow(
            label = "Teach cost", value = ""
        )
    }
}

@Composable
private fun CostRow(
    label: String, value: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f), text = label
        )

        OutlinedTextField(
            modifier = Modifier.weight(1f), value = value, onValueChange = {}, singleLine = true
        )

        Spacer(Modifier.width(8.dp))

        Text("$")
    }
}

@Composable
private fun BallBoySection() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(stringResource(R.string.ball_boy))

        OutlinedButton(
            modifier = Modifier.fillMaxWidth(), onClick = { /* select person */ }) {
            Text(stringResource(R.string.select_ball_boy))
        }

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = "",
            onValueChange = {},
            label = { Text(stringResource(R.string.ball_boy_cost)) },
            singleLine = true
        )
    }
}

@Composable
private fun StatusSection() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(stringResource(R.string.class_status))

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
            modifier = Modifier.width(80.dp), value = "", onValueChange = {}, singleLine = true
        )

        Spacer(Modifier.width(8.dp))

        Text("weeks")
    }
}
