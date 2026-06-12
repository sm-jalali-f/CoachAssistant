package com.freez.domain.repositories

import com.freez.domain.model.person.StudentPerson

interface StudentRepository {
    suspend fun getStudents(): List<StudentPerson>
}