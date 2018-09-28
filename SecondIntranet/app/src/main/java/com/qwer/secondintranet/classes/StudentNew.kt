package com.qwer.secondintranet.classes

import java.util.*

class StudentNew(yearOfStudy_: Int, faculty_: String, specialization_: String,
                 firstname_: String, lastname_: String, dateOfBirth_: Date, telNumber_: String, email_: String) : User(
        firstname_, lastname_, dateOfBirth_, telNumber_, email_) {

    private val yearOfStudy: Int
    private val faculty: String
    private val specialization: String
    private val cources: Vector<Course>? = null

    init{
        yearOfStudy = yearOfStudy_
        faculty = faculty_
        specialization = specialization_
    }

}