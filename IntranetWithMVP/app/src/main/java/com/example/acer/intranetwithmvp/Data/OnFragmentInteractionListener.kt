package com.example.acer.intranetwithmvp.Data

interface OnFragmentInteractionListener {
    fun getCourse(): ArrayList<Course>
    fun getTeachers(): ArrayList<Teacher>
    fun getStudents(): ArrayList<Student>
    fun addStudent(name: String, id: String, gpa: String)
    fun addTeacher(name: String,id: String,salary: String,course: String)
    fun startStudentCreateFragment()
    fun startTeacherCreateFragment()
    fun startCourseCreateFragment()
    fun onResumeData()
}