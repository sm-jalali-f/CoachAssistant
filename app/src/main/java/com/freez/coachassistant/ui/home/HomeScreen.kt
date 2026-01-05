package com.freez.coachassistant.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.freez.coachassistant.R
import com.freez.domain.model.AppDate
import com.freez.domain.model.Money

private val HEADER_HEIGHT = 200.dp

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel()) {

    val uiState by homeViewModel.state.collectAsState()
    val listState = rememberLazyListState()
    val headerHeight = HEADER_HEIGHT
    val headerHeightPx = with(LocalDensity.current) { headerHeight.toPx() }

    val collapseOffset by remember {
        derivedStateOf {
            minOf(
                listState.firstVisibleItemScrollOffset.toFloat(),
                headerHeightPx
            )
        }
    }
    val collapseFraction = collapseOffset / headerHeightPx
    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            if (collapseFraction < 1f) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(headerHeight)
                        .graphicsLayer {
                            translationY = -collapseOffset
                            alpha = 1f - collapseFraction
                        }
                ) {
                    HomeHeader(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.primary),
                        uiState.greeting, uiState.monthlyReport
                    )
                }
            }
        }
        stickyHeader {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 8.dp,
                        end = 8.dp,
                    ),
                horizontalAlignment = Alignment.Start
            ) {
                HorizontalDaysList(
                    dates = uiState.datesList,
                    today = uiState.today,
                    selectedDay = uiState.selectedDate,
                    dayClicked = {
                        homeViewModel.onIntent(HomeIntent.SelectDate(it))
                    })

                Spacer(Modifier.height(24.dp))
                val selectedDate =
                    "${uiState.selectedDate?.day} ${uiState.selectedDate?.monthName} ${uiState.selectedDate?.year}"
                Text(
                    text = stringResource(R.string.class_session, selectedDate),
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(4.dp))
            }
        }
        if (uiState.classSessionList.isNullOrEmpty()) {
            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 25.dp),
                    textAlign = TextAlign.Center,
                    text = stringResource(R.string.there_is_no_class),
                    fontWeight = FontWeight.Light
//                    fontFamily = MaterialTheme.typography.bodySmall
                )
            }
        }
        items(uiState.classSessionList?.size ?: 0) { index ->
            SessionItem(
                Modifier.padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp),
                studentName = "Student $index",
                time = "18:00",
                court = "Court ${index + 1}"
            )
        }
    }
}

@Composable
fun HomeHeader(modifier: Modifier, greeting: GreetingState?, monthlyReport: MonthlyReport?) {
    Column(modifier = modifier.padding(10.dp)) {
        ProfileAndGreeting(
            modifier = Modifier.fillMaxWidth(),
            greeting?.name ?: stringResource(R.string.unknown),
            greeting?.greeting ?: stringResource(R.string.hi)
        )
        Spacer(Modifier.height(10.dp))
        MonthlyStats(monthlyReport?.sessionCount ?: 0, monthlyReport?.income)
        Spacer(Modifier.height(10.dp))
    }
}

@Composable
fun HorizontalDaysList(
    dates: List<AppDate>?,
    today: AppDate?,
    selectedDay: AppDate?,
    dayClicked: (AppDate) -> Unit
) {

    val listState = rememberLazyListState()

    val firstVisibleMonth by remember {
        derivedStateOf {
            val firstVisibleItem = listState.layoutInfo
                .visibleItemsInfo
                .firstOrNull()

            firstVisibleItem?.index
        }
    }
    val currentMonth by remember {
        derivedStateOf {
            firstVisibleMonth?.let { index ->
                dates?.getOrNull(index)?.monthName
            }
        }
    }
    val currentYear by remember {
        derivedStateOf {
            firstVisibleMonth?.let { index ->
                dates?.getOrNull(index)?.year
            }
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(currentMonth ?: "-", fontWeight = FontWeight.Bold)
        Spacer(Modifier.width(15.dp))
        Text("${currentYear ?: "-"}", fontWeight = FontWeight.Bold)
    }
    Spacer(Modifier.height(15.dp))

    val visibleCount by remember {
        derivedStateOf {
            listState.layoutInfo.visibleItemsInfo.size

        }
    }
    LaunchedEffect(selectedDay) {
        dates?.let { d ->
            selectedDay?.let { sd ->
                listState.animateScrollToItem(maxOf(0, d.indexOf(sd) - (visibleCount - 1) / 2))
            }
        }
    }

    if (dates != null) {
        LazyRow(
            state = listState,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(dates.size) { index ->
//            val dayNumber = index + 1
                val isSelected = dates[index] == selectedDay
                val isToday = dates[index] == today

                DayItem(
                    day = dates[index].day,
                    isToday = isToday,
                    weekday = dates[index].dayOfWeek,
                    selected = isSelected,
                    onClick = { dayClicked(dates[index]) }
                )
            }
        }
    }
}

@Composable
fun MonthlyStats(totalClasses: Int, money: Money?) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        StatBox(
            modifier = Modifier.weight(0.5f),
            title = stringResource(R.string.monthly_session_count),
            value = totalClasses.toString()
        )
        StatBox(
            modifier = Modifier.weight(0.5f),
            title = stringResource(R.string.monthly_income),
            value = "${money?.currency ?: ""} ${money?.amount ?: 0}"
        )
    }
}

@Composable
fun ProfileAndGreeting(modifier: Modifier, name: String, greetingExpression: String) {
    Box {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text("Img", fontSize = 12.sp)
            }

            Spacer(Modifier.width(12.dp))

            Column {
                Text(
                    text = name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = greetingExpression,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Composable
fun StatBox(modifier: Modifier = Modifier, title: String, value: String) {
    Column(
        modifier = modifier
            .padding(horizontal = 4.dp)
            .border(1.dp, Color.Gray, RoundedCornerShape(12.dp))
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(title, fontSize = 14.sp, color = MaterialTheme.colorScheme.onPrimary)
        Spacer(Modifier.height(8.dp))
        Text(
            value,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun DayItem(
    day: Int,
    weekday: String,
    isToday: Boolean,
    selected: Boolean,
    onClick: () -> Unit
) {
    val bg =
        if (selected) MaterialTheme.colorScheme.primary
        else Color.Transparent
    val textColor =
        if (selected) MaterialTheme.colorScheme.onPrimary
        else if (isToday) MaterialTheme.colorScheme.primary
        else MaterialTheme.colorScheme.onBackground
    val borderColor =
        if (selected) MaterialTheme.colorScheme.primary
        else if (isToday) MaterialTheme.colorScheme.primary
        else MaterialTheme.colorScheme.onBackground

    Column(
        modifier = Modifier
            .width(60.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, borderColor, RoundedCornerShape(12.dp))
            .background(bg)
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(day.toString(), fontSize = 18.sp, fontWeight = FontWeight.Bold, color = textColor)
        Text(weekday, fontSize = 12.sp, color = textColor)
    }
}

@Composable
fun SessionItem(modifier: Modifier, studentName: String, time: String, court: String) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, Color.Gray, RoundedCornerShape(12.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape),
//                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                Text("img", fontSize = 10.sp)
            }
            Spacer(Modifier.width(8.dp))
            Text(studentName)
        }

        Column(horizontalAlignment = Alignment.End) {
            Text(time, fontWeight = FontWeight.Bold)
            Text(court, fontSize = 12.sp, color = Color.Gray)
        }
    }
}

fun getWeekday(day: Int): String {
    val labels = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
    return labels[day % 7]
}


//@Preview
//@Composable
//fun DashboardScreenPreview() {
//    DashboardScreen(
//        name = "John Doe",
//        totalClasses = 45,
//        monthIncome = 2300,
//        selectedDay = 7,
//        onDayClick = {}
//    )
//}


@Preview
@Composable
fun StatBoxPreview() {
    HomeScreen()
//    Box() {
//        Text("Hi")
//    }

//    StatBox(title = "تعد اد کلاس ماه", value = "45")
}

/*


@Preview
@Composable
fun DayItemPreview() {
    DayItem(day = 7, weekday = "Sun", selected = true, onClick = {})
}

@Preview
@Composable
fun DayItemUnselectedPreview() {
    DayItem(day = 8, weekday = "Mon", selected = false, onClick = {})
}

@Preview
@Composable
fun SessionItemPreview() {
    SessionItem(studentName = "Student 1", time = "18:00", court = "Court 1")
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}






*/
