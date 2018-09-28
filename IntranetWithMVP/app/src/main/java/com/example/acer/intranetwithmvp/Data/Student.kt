package com.example.acer.intranetwithmvp.Data

class Student(id: Int?, name: String,var gpa: Double): User(id,name) {
    override fun toString(): String {
        return super.toString()+ "Student(gpa=$gpa)"
    }

}