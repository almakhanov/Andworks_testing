package com.example.acer.intranetwithmvp.Data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

object DataEntities {
    @Entity(tableName = "Students") class StudentEntity(@PrimaryKey(autoGenerate = true) var id: Int?, var name: String, var gpa: Double){
        override fun toString(): String{
            return "$id   $name $gpa"
        }
    }
    @Entity(tableName = "Teachers") class TeacherEntity(@PrimaryKey(autoGenerate = true) var id: Int?, var name: String, var salary: Int,var course: String){}

    @Entity(tableName = "Courses") class CourseEntity(@PrimaryKey(autoGenerate = true) var id: Int?, var title: String, var description: String,var credits: Int,var teacherId: Int){}

    @Entity(tableName = "CourseStudents") class CourseStudents(@PrimaryKey var courseId: Int?,var studentId: Int?)
}