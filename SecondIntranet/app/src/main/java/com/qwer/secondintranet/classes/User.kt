package com.qwer.secondintranet.classes

import java.util.*

abstract class User(firstname_: String, lastname_: String, dateOfBirth_: Date, telNumber_: String, email_: String) {
    private val id: String
    private val username: String
    private val firstname: String
    private val lastname: String
    private val password: String
    private val dateOfRegistration: Date
    private val dateOfBirth: Date
    private val telNumber: String
    private val email: String?

    init{
        firstname = firstname_
        lastname = lastname_
        username = "${firstname[0]}_$lastname"
        password = "12345"
        telNumber = telNumber_
        email = email_
        dateOfBirth = dateOfBirth_
        dateOfRegistration = Date()
        @Suppress("DEPRECATION")
        id = "${dateOfRegistration.year}_$username"
    }

}