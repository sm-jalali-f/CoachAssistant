package com.freez.coachassistant.ui.newEvent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.freez.coachassistant.R
import com.freez.coachassistant.util.PersianNumberFormatter
import com.freez.coachassistant.util.persianLabel
import com.freez.domain.model.AppDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewClassEventScreen(
    onClose: () -> Unit,
    onSave: () -> Unit,
    viewModel: NewEventViewModel,
) {
    val state by viewModel.state.collectAsState()
    val event = state.tennisEvent

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.new_event_class)) },
                navigationIcon = {
                    IconButton(onClick = onClose) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.close),
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer,
                ),
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            DateTimeSection(
                startDateTime = event.startDateTime,
                endDateTime = event.endDateTime,
                onDateClick = { viewModel.onIntent(NewEventIntent.ShowDialog(NewEventDialog.DatePicker)) },
                onStartTimeClick = { viewModel.onIntent(NewEventIntent.ShowDialog(NewEventDialog.StartTimePicker)) },
                onEndTimeClick = { viewModel.onIntent(NewEventIntent.ShowDialog(NewEventDialog.EndTimePicker)) },
            )

            PlayersSection(
                players = event.players,
                onAddClick = { viewModel.onIntent(NewEventIntent.ShowDialog(NewEventDialog.PlayerPicker)) },
                onRemovePlayer = { viewModel.onIntent(NewEventIntent.RemovePlayer(it)) },
            )

            TeachingCostSection(
                teachingCost = state.teachingCost,
                onCostChange = { viewModel.onIntent(NewEventIntent.UpdateTeachingCost(it)) },
            )

            ClubSection(
                selectedClubName = state.selectedClub?.name,
                clubRentCost = state.clubRentCost,
                selectedBallBoyName = event.ballBoy?.person?.name,
                ballBoyCost = state.ballBoyCost,
                onSelectClub = { viewModel.onIntent(NewEventIntent.ShowDialog(NewEventDialog.ClubPicker)) },
                onClubRentChange = { viewModel.onIntent(NewEventIntent.UpdateClubRentCost(it)) },
                onSelectBallBoy = { viewModel.onIntent(NewEventIntent.ShowDialog(NewEventDialog.BallBoyPicker)) },
                onBallBoyCostChange = { viewModel.onIntent(NewEventIntent.UpdateBallBoyCost(it)) },
            )

            StatusSection(
                statusLabel = event.eventStatus.persianLabel(),
                onClick = { viewModel.onIntent(NewEventIntent.ShowDialog(NewEventDialog.StatusPicker)) },
            )

            ReserveSection(
                weeks = state.reserveWeeks,
                onWeeksChange = { viewModel.onIntent(NewEventIntent.SetReserveWeeks(it)) },
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                onClick = {
                    viewModel.onIntent(NewEventIntent.Save)
                    onSave()
                },
            ) {
                Text(stringResource(R.string.save))
            }

            Spacer(Modifier.height(8.dp))
        }
    }

    when (state.activeDialog) {
        NewEventDialog.DatePicker -> JalaliCalendarDatePicker(
            isVisible = true,
            selectedDate = event.startDateTime.appDate,
            onDateSelected = { year, month, day ->
                viewModel.onIntent(NewEventIntent.SelectDateFromPicker(year, month, day))
            },
            onDismiss = { viewModel.onIntent(NewEventIntent.DismissDialog) },
        )

        NewEventDialog.StartTimePicker -> TimePickerDialog(
            title = stringResource(R.string.select_start_time),
            initialHour = event.startDateTime.hour.value,
            initialMinute = event.startDateTime.minute.value,
            onConfirm = { h, m -> viewModel.onIntent(NewEventIntent.SelectStartTime(h, m)) },
            onDismiss = { viewModel.onIntent(NewEventIntent.DismissDialog) },
        )

        NewEventDialog.EndTimePicker -> TimePickerDialog(
            title = stringResource(R.string.select_end_time),
            initialHour = event.endDateTime.hour.value,
            initialMinute = event.endDateTime.minute.value,
            onConfirm = { h, m -> viewModel.onIntent(NewEventIntent.SelectEndTime(h, m)) },
            onDismiss = { viewModel.onIntent(NewEventIntent.DismissDialog) },
        )

        NewEventDialog.PlayerPicker -> MultiSelectPlayerDialog(
            players = state.availablePlayers,
            initiallySelected = event.players,
            onConfirm = { viewModel.onIntent(NewEventIntent.ConfirmPlayers(it)) },
            onDismiss = { viewModel.onIntent(NewEventIntent.DismissDialog) },
        )

        NewEventDialog.ClubPicker -> SingleSelectClubDialog(
            clubs = state.availableClubs,
            selectedClub = state.selectedClub,
            onSelect = { viewModel.onIntent(NewEventIntent.SelectClub(it)) },
            onDismiss = { viewModel.onIntent(NewEventIntent.DismissDialog) },
        )

        NewEventDialog.BallBoyPicker -> SingleSelectBallBoyDialog(
            ballBoys = state.availableBallBoys,
            selectedBallBoy = event.ballBoy,
            onSelect = { viewModel.onIntent(NewEventIntent.SelectBallBoy(it)) },
            onDismiss = { viewModel.onIntent(NewEventIntent.DismissDialog) },
        )

        NewEventDialog.StatusPicker -> StatusPickerDialog(
            currentStatus = event.eventStatus,
            onSelect = { viewModel.onIntent(NewEventIntent.SelectStatus(it)) },
            onDismiss = { viewModel.onIntent(NewEventIntent.DismissDialog) },
        )

        null -> Unit
    }
}

@Composable
private fun DateTimeSection(
    startDateTime: AppDateTime,
    endDateTime: AppDateTime,
    onDateClick: () -> Unit,
    onStartTimeClick: () -> Unit,
    onEndTimeClick: () -> Unit,
) {
    SectionCard(title = stringResource(R.string.date_and_time)) {
        val date = startDateTime.appDate
        DateTimeClickableRow(
            label = stringResource(R.string.date),
            value = "${date.dayOfWeek}، ${PersianNumberFormatter.toPersianDigits(date.day.toString())} ${date.monthName} ${PersianNumberFormatter.toPersianDigits(date.year.toString())}",
            onClick = onDateClick,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            DateTimeClickableRow(
                label = stringResource(R.string.start_colon),
                value = PersianNumberFormatter.formatTime(
                    startDateTime.hour.value,
                    startDateTime.minute.value,
                ),
                onClick = onStartTimeClick,
                modifier = Modifier.weight(1f),
            )
            DateTimeClickableRow(
                label = stringResource(R.string.end_colon),
                value = PersianNumberFormatter.formatTime(
                    endDateTime.hour.value,
                    endDateTime.minute.value,
                ),
                onClick = onEndTimeClick,
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@Composable
private fun PlayersSection(
    players: List<com.freez.domain.model.person.StudentPerson>,
    onAddClick: () -> Unit,
    onRemovePlayer: (com.freez.domain.model.person.StudentPerson) -> Unit,
) {
    SectionCard(title = stringResource(R.string.players)) {
        if (players.isNotEmpty()) {
            FlowRowSpaced {
                players.forEach { player ->
                    PlayerChip(player = player, onRemove = { onRemovePlayer(player) })
                }
            }
        }
        AddActionButton(
            text = stringResource(R.string.add_new_player),
            onClick = onAddClick,
        )
    }
}

@Composable
private fun TeachingCostSection(
    teachingCost: Long?,
    onCostChange: (Long?) -> Unit,
) {
    SectionCard(title = stringResource(R.string.teaching_cost)) {
        CurrencyTextField(
            value = teachingCost,
            onValueChange = onCostChange,
            label = stringResource(R.string.teaching_cost_hint),
        )
    }
}

@Composable
private fun ClubSection(
    selectedClubName: String?,
    clubRentCost: Long?,
    selectedBallBoyName: String?,
    ballBoyCost: Long,
    onSelectClub: () -> Unit,
    onClubRentChange: (Long?) -> Unit,
    onSelectBallBoy: () -> Unit,
    onBallBoyCostChange: (Long?) -> Unit,
) {
    SectionCard(title = stringResource(R.string.club)) {
        SelectableChip(
            label = selectedClubName ?: stringResource(R.string.select_club),
            onClick = onSelectClub,
        )
        CurrencyTextField(
            value = clubRentCost,
            onValueChange = onClubRentChange,
            label = stringResource(R.string.club_rent_cost),
        )
        SelectableChip(
            label = selectedBallBoyName ?: stringResource(R.string.select_ball_boy_optional),
            onClick = onSelectBallBoy,
        )
        CurrencyTextField(
            value = ballBoyCost,
            onValueChange = onBallBoyCostChange,
            label = stringResource(R.string.ball_boy_cost),
            defaultZero = true,
        )
    }
}

@Composable
private fun StatusSection(
    statusLabel: String,
    onClick: () -> Unit,
) {
    SectionCard(title = stringResource(R.string.class_status)) {
        SelectableChip(label = statusLabel, onClick = onClick)
    }
}

@Composable
private fun ReserveSection(
    weeks: Int,
    onWeeksChange: (Int) -> Unit,
) {
    SectionCard(title = stringResource(R.string.reserve_section)) {
        HintText(stringResource(R.string.reserve_hint))
        Spacer(Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(stringResource(R.string.reserve_for))
            Row(verticalAlignment = Alignment.CenterVertically) {
                WeekStepper(weeks = weeks, onWeeksChange = onWeeksChange)
                Spacer(Modifier.width(8.dp))
                Text(stringResource(R.string.weeks))
            }
        }
    }
}
