package com.example.acer.intranetwithmvp.Models

import com.example.acer.intranetwithmvp.Data.DataEntities
import com.example.acer.intranetwithmvp.Data.Student
import io.reactivex.Flowable

interface StudentModelListener {
    fun addStudent(s: DataEntities.StudentEntity)
    fun addTeacher(t: DataEntities.TeacherEntity)
    //fun getCourses()
    fun addCourse(c: DataEntities.CourseEntity)
    fun loadAllFromDatabase(): Flowable<ArrayList<Any>>
}