package com.freez.domain.model.person

data class Person(val name: String, val phoneNumber: String, val roles: Set<Role>)


data class CoachPerson private constructor(
    val person: Person,
    val coachData: Role.Coach
) {
    companion object {
        fun from(person: Person): CoachPerson? {
            val role = person.roles.filterIsInstance<Role.Coach>().firstOrNull()
            return role?.let { CoachPerson(person, it) }
        }
    }
}

data class StudentPerson private constructor(
    val person: Person,
    val studentData: Role.Student
) {
    companion object {
        fun from(person: Person): StudentPerson? {
            val role = person.roles.filterIsInstance<Role.Student>().firstOrNull()
            return role?.let { StudentPerson(person, it) }
        }
    }
}

data class BallBoyPerson private constructor(
    val person: Person,
    val studentData: Role.BallBoy
) {
    companion object {
        fun from(person: Person): BallBoyPerson? {
            val role = person.roles.filterIsInstance<Role.BallBoy>().firstOrNull()
            return role?.let { BallBoyPerson(person, it) }
        }
    }
}