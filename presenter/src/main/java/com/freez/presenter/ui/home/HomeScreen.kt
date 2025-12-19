package com.freez.presenter.ui.home

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.freez.domain.model.Money
import com.freez.presenter.R

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = viewModel()) {

    val uiState by homeViewModel.uiState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()) // Because top content might need scrolling on small screens
    ) {

        // ---------------------------
        // Header: Profile + Greeting
        // ---------------------------
        ProfileAndGreeting(uiState.greeting.name, uiState.greeting.greeting)

        Spacer(Modifier.height(20.dp))

        // ---------------------------
        // Monthly Stats
        // ---------------------------
        MonthlyStats(uiState.monthlyReport.sessionCount, uiState.monthlyReport.income)

        Spacer(Modifier.height(20.dp))

        // ---------------------------
        // Horizontal Day Calendar
        // ---------------------------
//        LazyRow(
//            horizontalArrangement = Arrangement.spacedBy(8.dp)
//        ) {
//            items(30) { index ->
//                val dayNumber = index + 1
//                val isSelected = dayNumber == selectedDay
//
//                DayItem(
//                    day = dayNumber,
//                    weekday = getWeekday(dayNumber),
//                    selected = isSelected,
//                    onClick = { onDayClick(dayNumber) }
//                )
//            }
//        }

        Spacer(Modifier.height(24.dp))

        Text("Class sessions:", fontWeight = FontWeight.Bold)

        Spacer(Modifier.height(12.dp))

        // ---------------------------
        // Vertical Classes List
        // ---------------------------
        /*LazyColumn(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(8) { index ->
                SessionItem(
                    studentName = "Student $index",
                    time = "18:00",
                    court = "Court ${index + 1}"
                )
            }
        }*/
    }
}

@Composable
fun MonthlyStats(totalClasses: Int, money: Money) {
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
            value = "${money.currency} ${money.amount}"
        )
    }
}

@Composable
fun ProfileAndGreeting(name: String, greetingExpression: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            Text("Img", fontSize = 12.sp)
        }

        Spacer(Modifier.width(12.dp))

        Column {
            Text(name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(greetingExpression, fontSize = 14.sp, color = Color.Gray)
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
        Text(title, fontSize = 14.sp)
        Spacer(Modifier.height(8.dp))
        Text(value, fontSize = 22.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun DayItem(day: Int, weekday: String, selected: Boolean, onClick: () -> Unit) {
    val bg = if (selected) Color(0xFF007BFF) else Color.Transparent
    val textColor = if (selected) Color.White else Color.Black

    Column(
        modifier = Modifier
            .width(60.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, Color.Gray, RoundedCornerShape(12.dp))
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
fun SessionItem(studentName: String, time: String, court: String) {
    Row(
        modifier = Modifier
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
                    .clip(CircleShape)
                    .background(Color.LightGray),
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
//    HomeScreen()
    Box() {
        Text("Hi")
    }

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
