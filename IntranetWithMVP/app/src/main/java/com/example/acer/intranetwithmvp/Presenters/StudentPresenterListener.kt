package com.example.acer.intranetwithmvp.Presenters

import com.example.acer.intranetwithmvp.Data.DataEntities
import com.example.acer.intranetwithmvp.Data.Student
import com.example.acer.intranetwithmvp.Data.Teacher
import com.example.acer.intranetwithmvp.Models.StudentModelListener

interface StudentPresenterListener {
        companion object {

            var modelListener: StudentModelListener? = null
        }
    fun getStudentSet(): ArrayList<Student>
    fun getTeacherSet(): ArrayList<Teacher>
    //fun getCourseSet(set: List<DataEntities.CourseEntity>)

}