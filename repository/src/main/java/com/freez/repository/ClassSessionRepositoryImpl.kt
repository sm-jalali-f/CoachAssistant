package com.freez.repository

import com.freez.datasource.database.dao.ClassSessionDao
import com.freez.datasource.database.dao.CourtDao
import com.freez.datasource.database.dao.StudentClassDao
import com.freez.datasource.database.dao.StudentDao
import com.freez.datasource.database.entity.ClassSessionEntity
import com.freez.datasource.database.entity.CourtEntity
import com.freez.datasource.database.entity.StudentClassEntity
import com.freez.datasource.database.entity.StudentEntity
import com.freez.datasource.database.model.SessionStatus
import com.freez.domain.model.AppDate
import com.freez.domain.model.AppDateTime
import com.freez.domain.model.ClassEvent
import com.freez.domain.model.Club
import com.freez.domain.model.EventStatus
import com.freez.domain.model.Hour
import com.freez.domain.model.Minute
import com.freez.domain.model.Money
import com.freez.domain.model.TennisCourt
import com.freez.domain.model.person.Person
import com.freez.domain.model.person.Role
import com.freez.domain.model.person.StudentPerson
import com.freez.domain.repositories.ClassSessionRepository
import java.math.BigDecimal
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

class ClassSessionRepositoryImpl @Inject constructor(
    private val classSessionDao: ClassSessionDao,
    private val courtDao: CourtDao,
    private val studentClassDao: StudentClassDao,
    private val studentDao: StudentDao,
) : ClassSessionRepository {

    override suspend fun getSessions(): List<ClassEvent> {
        val courtsById = courtDao.getAll().associateBy { it.id }
        val studentsById = studentDao.getAll().associateBy { it.id }

        return classSessionDao.getAll().map { session ->
            val studentLinks = studentClassDao.getStudentsOfSession(session.id)
            session.toClassEvent(
                court = session.courtId?.let { courtsById[it] },
                studentLinks = studentLinks,
                studentsById = studentsById,
            )
        }
    }
}

private fun ClassSessionEntity.toClassEvent(
    court: CourtEntity?,
    studentLinks: List<StudentClassEntity>,
    studentsById: Map<Long, StudentEntity>,
): ClassEvent {
    val players = studentLinks.mapNotNull { link ->
        studentsById[link.studentId]?.toStudentPerson()
    }
    val teachingPrice = if (isTeaching) {
        studentLinks.sumOf { it.teachingPriceShare }.toMoneyOrNull()
    } else {
        null
    }

    return ClassEvent(
        startDateTime = startDateTime.toAppDateTime(),
        endDateTime = endDateTime.toAppDateTime(),
        court = court?.toTennisCourt(),
        players = players,
        coach = null,
        ballBoy = null,
        courtRentPrice = courtPrice.toMoneyOrNull(),
        ballBoyPrice = ballBoyPrice.toMoneyOrNull(),
        teachingPrice = teachingPrice,
        eventStatus = status.toEventStatus(),
        courtRentDiscountPercent = BigDecimal.valueOf(discount),
        teachingFeeDiscountPercent = BigDecimal.ZERO,
    )
}

private fun CourtEntity.toTennisCourt(): TennisCourt {
    val club = Club(
        name = name,
        latitude = null,
        longitude = null,
        rentCourt = defaultCourtCost.toMoneyOrNull(),
        ballBoyPrice = defaultBallBoyCost.toMoneyOrNull(),
        address = null,
        phoneNumber = null,
        manager = null,
    )
    return TennisCourt(club = club, number = null)
}

private fun StudentEntity.toStudentPerson(): StudentPerson {
    val person = Person(
        name = name,
        phoneNumber = "",
        roles = setOf(Role.Student(skillLevel = null, color = 0L)),
    )
    return StudentPerson.from(person)!!
}

private fun SessionStatus.toEventStatus(): EventStatus = when (this) {
    SessionStatus.EMPTY_RESERVE -> EventStatus.ReservedWithoutStudent
    SessionStatus.RESERVE -> EventStatus.Reserved
    SessionStatus.EXECUTED -> EventStatus.Done
    SessionStatus.CANCEL_BY_COURT -> EventStatus.CancelByCourt
    SessionStatus.CANCEL_BY_COACH -> EventStatus.CancelByCoach
    SessionStatus.CANCEL_BY_STUDENT -> EventStatus.CancelByStudent
    SessionStatus.SOLD -> EventStatus.Sold
}

private fun Long.toAppDateTime(): AppDateTime {
    val calendar = Calendar.getInstance().apply { timeInMillis = this@toAppDateTime }
    val locale = Locale.getDefault()
    val appDate = AppDate(
        year = calendar.get(Calendar.YEAR),
        month = calendar.get(Calendar.MONTH) + 1,
        day = calendar.get(Calendar.DAY_OF_MONTH),
        dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, locale).orEmpty(),
        monthName = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, locale).orEmpty(),
    )
    return AppDateTime(
        appDate = appDate,
        hour = Hour.of(calendar.get(Calendar.HOUR_OF_DAY)),
        minute = Minute.of(calendar.get(Calendar.MINUTE)),
    )
}

private fun Long?.toMoneyOrNull(): Money? =
    this?.let { Money(amount = BigDecimal.valueOf(it)) }
