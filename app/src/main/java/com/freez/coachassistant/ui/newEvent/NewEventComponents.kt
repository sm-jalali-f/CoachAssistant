package com.freez.coachassistant.ui.newEvent

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.miaadrajabi.persiandatepicker.ui.DialogGravity
import com.miaadrajabi.persiandatepicker.ui.PersianDatePicker
import com.miaadrajabi.persiandatepicker.ui.PersianDatePickerBuilder
import com.miaadrajabi.persiandatepicker.ui.PresentationStyle
import com.miaadrajabi.persiandatepicker.ui.SelectionMode
import com.miaadrajabi.persiandatepicker.ui.SelectionResult
import com.miaadrajabi.persiandatepicker.utils.PersianDate
import com.freez.coachassistant.util.PersianNumberFormatter
import com.freez.coachassistant.util.persianLabel
import com.freez.domain.model.AppDate
import com.freez.domain.model.Club
import com.freez.domain.model.EventStatus
import com.freez.domain.model.Hour
import com.freez.domain.model.Minute
import com.freez.domain.model.person.BallBoyPerson
import com.freez.domain.model.person.StudentPerson

@Composable
fun SectionCard(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    ElevatedCard(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        ),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
            )
            content()
        }
    }
}

@Composable
fun CurrencyTextField(
    value: Long?,
    onValueChange: (Long?) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    defaultZero: Boolean = false,
) {
    val digitsFromValue = when {
        value != null -> value.toString()
        defaultZero -> "0"
        else -> ""
    }
    var textFieldValue by remember {
        mutableStateOf(TextFieldValue(digitsFromValue, TextRange(digitsFromValue.length)))
    }

    LaunchedEffect(value, defaultZero) {
        val expected = when {
            value != null -> value.toString()
            defaultZero -> "0"
            else -> ""
        }
        if (expected != textFieldValue.text) {
            textFieldValue = TextFieldValue(expected, TextRange(expected.length))
        }
    }

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = textFieldValue,
        onValueChange = { newValue ->
            val digits = newValue.text.filter { it.isDigit() }.take(15)
            val cursor = newValue.selection.start.coerceIn(0, digits.length)
            textFieldValue = TextFieldValue(digits, TextRange(cursor))
            onValueChange(digits.toLongOrNull())
        },
        label = { Text(label) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = CurrencyVisualTransformation(),
        suffix = { Text("تومان") },
        shape = RoundedCornerShape(12.dp),
    )
}

@Composable
fun SelectableChip(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    accentColor: Color = MaterialTheme.colorScheme.primary,
    trailing: (@Composable () -> Unit)? = null,
) {
    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, accentColor.copy(alpha = 0.4f), RoundedCornerShape(12.dp))
            .clickable(onClick = onClick),
        color = accentColor.copy(alpha = 0.08f),
        shape = RoundedCornerShape(12.dp),
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = label,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyMedium,
            )
            trailing?.invoke()
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PlayerChip(
    player: StudentPerson,
    onRemove: () -> Unit,
) {
    val color = Color(player.studentData.color)
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = color.copy(alpha = 0.15f),
        modifier = Modifier.border(1.dp, color.copy(alpha = 0.5f), RoundedCornerShape(20.dp)),
    ) {
        Row(
            modifier = Modifier.padding(start = 12.dp, end = 4.dp, top = 6.dp, bottom = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(color),
            )
            Spacer(Modifier.width(8.dp))
            Text(player.person.name, style = MaterialTheme.typography.bodyMedium)
            IconButton(onClick = onRemove, modifier = Modifier.size(28.dp)) {
                Icon(
                    Icons.Default.Close,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                )
            }
        }
    }
}

@Composable
fun JalaliCalendarDatePicker(
    isVisible: Boolean,
    selectedDate: AppDate,
    onDateSelected: (year: Int, month: Int, day: Int) -> Unit,
    onDismiss: () -> Unit,
) {
    val initialDate = remember(selectedDate) {
        PersianDate(selectedDate.year, selectedDate.month, selectedDate.day)
    }

    PersianDatePicker(
        isVisible = isVisible,
        config = PersianDatePickerBuilder()
            .selectionMode(SelectionMode.SINGLE)
            .presentation(PresentationStyle.DIALOG)
            .gravity(DialogGravity.CENTER)
            .sizeFraction(0.95f, null)
            .singleTitle("انتخاب تاریخ")
            .showTodayButton(true)
            .todayButtonText("امروز")
            .enableYearPicker(true)
            .initialSelectedDates(setOf(initialDate))
            .dismissOnBackPress(true)
            .dismissOnClickOutside(true)
            .build(),
        onDismiss = onDismiss,
        onResult = { result ->
            if (result is SelectionResult.Single) {
                val date = result.date
                onDateSelected(date.year, date.month, date.day)
            }
        },
    )
}

@Composable
fun TimePickerDialog(
    title: String,
    initialHour: Int,
    initialMinute: Int,
    onConfirm: (Hour, Minute) -> Unit,
    onDismiss: () -> Unit,
) {
    var hour by remember { mutableStateOf(initialHour) }
    var minute by remember { mutableStateOf(initialMinute) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(title) },
        text = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Text(
                    text = PersianNumberFormatter.formatTime(hour, minute),
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    TimeStepper(
                        label = "ساعت",
                        value = hour,
                        range = 0..23,
                        onValueChange = { hour = it },
                    )
                    TimeStepper(
                        label = "دقیقه",
                        value = minute,
                        range = 0..59,
                        step = 5,
                        onValueChange = { minute = it },
                    )
                }
            }
        },
        confirmButton = {
            Button(onClick = {
                onConfirm(Hour.of(hour), Minute.of(minute))
            }) {
                Text("تأیید")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("انصراف") }
        },
    )
}

@Composable
private fun TimeStepper(
    label: String,
    value: Int,
    range: IntRange,
    step: Int = 1,
    onValueChange: (Int) -> Unit,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(label, style = MaterialTheme.typography.labelMedium)
        Spacer(Modifier.height(8.dp))
        FilledTonalButton(onClick = {
            val next = value + step
            onValueChange(if (next > range.last) range.first else next)
        }) {
            Text("+")
        }
        Text(
            text = PersianNumberFormatter.toPersianDigits("%02d".format(value)),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(vertical = 8.dp),
        )
        FilledTonalButton(onClick = {
            val prev = value - step
            onValueChange(if (prev < range.first) range.last else prev)
        }) {
            Text("−")
        }
    }
}

@Composable
fun MultiSelectPlayerDialog(
    players: List<StudentPerson>,
    initiallySelected: List<StudentPerson>,
    onConfirm: (List<StudentPerson>) -> Unit,
    onDismiss: () -> Unit,
) {
    var selected by remember {
        mutableStateOf(initiallySelected.toSet())
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("انتخاب شاگرد") },
        text = {
            LazyColumn(
                modifier = Modifier.height(280.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                items(players) { player ->
                    val isChecked = player in selected
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .clickable {
                                selected = if (isChecked) {
                                    selected - player
                                } else {
                                    selected + player
                                }
                            }
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Checkbox(
                            checked = isChecked,
                            onCheckedChange = { checked ->
                                selected = if (checked) selected + player else selected - player
                            },
                        )
                        Spacer(Modifier.width(8.dp))
                        Box(
                            modifier = Modifier
                                .size(12.dp)
                                .clip(CircleShape)
                                .background(Color(player.studentData.color)),
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(player.person.name)
                    }
                }
            }
        },
        confirmButton = {
            Button(onClick = { onConfirm(selected.toList()) }) {
                Text("تأیید")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("انصراف") }
        },
    )
}

@Composable
fun SingleSelectClubDialog(
    clubs: List<Club>,
    selectedClub: Club?,
    onSelect: (Club) -> Unit,
    onDismiss: () -> Unit,
) {
    InstantSelectListDialog(
        title = "انتخاب باشگاه",
        onDismiss = onDismiss,
    ) {
        items(clubs) { club ->
            InstantSelectRow(
                label = club.name,
                subtitle = club.address,
                isSelected = club == selectedClub,
                onClick = { onSelect(club) },
            )
        }
    }
}

@Composable
fun SingleSelectBallBoyDialog(
    ballBoys: List<BallBoyPerson>,
    selectedBallBoy: BallBoyPerson?,
    onSelect: (BallBoyPerson?) -> Unit,
    onDismiss: () -> Unit,
) {
    InstantSelectListDialog(
        title = "انتخاب توپ‌جمع‌کن",
        onDismiss = onDismiss,
    ) {
        item {
            InstantSelectRow(
                label = "بدون توپ‌جمع‌کن",
                subtitle = null,
                isSelected = selectedBallBoy == null,
                onClick = { onSelect(null) },
            )
        }
        items(ballBoys) { ballBoy ->
            InstantSelectRow(
                label = ballBoy.person.name,
                subtitle = null,
                isSelected = ballBoy == selectedBallBoy,
                onClick = { onSelect(ballBoy) },
            )
        }
    }
}

@Composable
fun StatusPickerDialog(
    currentStatus: EventStatus,
    onSelect: (EventStatus) -> Unit,
    onDismiss: () -> Unit,
) {
    InstantSelectListDialog(
        title = "وضعیت کلاس",
        onDismiss = onDismiss,
    ) {
        items(EventStatus.entries) { status ->
            InstantSelectRow(
                label = status.persianLabel(),
                subtitle = null,
                isSelected = status == currentStatus,
                onClick = { onSelect(status) },
            )
        }
    }
}

@Composable
fun WeekStepper(
    weeks: Int,
    onWeeksChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        FilledTonalIconButton(
            onClick = { onWeeksChange(weeks - 1) },
            enabled = weeks > 1,
            modifier = Modifier.size(36.dp),
        ) {
            Text("−", style = MaterialTheme.typography.titleMedium)
        }
        Text(
            text = PersianNumberFormatter.toPersianDigits(weeks.toString()),
            modifier = Modifier.width(28.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
        )
        FilledTonalIconButton(
            onClick = { onWeeksChange(weeks + 1) },
            modifier = Modifier.size(36.dp),
        ) {
            Text("+", style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Composable
private fun InstantSelectListDialog(
    title: String,
    onDismiss: () -> Unit,
    content: androidx.compose.foundation.lazy.LazyListScope.() -> Unit,
) {
    Dialog(onDismissRequest = onDismiss) {
        ElevatedCard(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(Modifier.height(12.dp))
                LazyColumn(
                    modifier = Modifier.heightIn(max = 360.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    content = content,
                )
            }
        }
    }
}

@Composable
private fun InstantSelectRow(
    label: String,
    subtitle: String?,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .background(
                if (isSelected) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
                else Color.Transparent,
            )
            .padding(horizontal = 12.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(label, fontWeight = FontWeight.Medium)
            subtitle?.let {
                Text(
                    it,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
        if (isSelected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

@Composable
fun DateTimeClickableRow(
    label: String,
    value: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    SelectableChip(
        label = "$label: $value",
        onClick = onClick,
        modifier = modifier,
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FlowRowSpaced(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    FlowRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        content = { content() },
    )
}

@Composable
fun HintText(text: String) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f),
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(12.dp),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            textAlign = TextAlign.Start,
        )
    }
}

@Composable
fun AddActionButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
    ) {
        Text("+ $text")
    }
}
