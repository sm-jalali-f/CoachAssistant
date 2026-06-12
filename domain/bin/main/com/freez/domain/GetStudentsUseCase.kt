package com.freez.domain

import com.freez.domain.model.person.StudentPerson
import com.freez.domain.repositories.StudentRepository

class GetStudentsUseCase constructor(
    private val studentRepository: StudentRepository
) {
    suspend fun getStudents(): List<StudentPerson> {
        return studentRepository.getStudents()
    }
}