package com.example.acer.intranetwithmvp.Presenters

import android.util.Log
import android.widget.Toast
import com.example.acer.intranetwithmvp.Data.*
import com.example.acer.intranetwithmvp.Models.UserDataModel
import com.example.acer.intranetwithmvp.Models.StudentModelListener
import com.example.acer.intranetwithmvp.Views.MainViewListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPresenter(var view: MainViewListener) : BasePresenter,StudentPresenterListener {


    companion object {
     private var entitiesList: ArrayList<Any> = ArrayList<Any>()
    }

    lateinit var studentModelListener: StudentModelListener
    var students: ArrayList<Student> = ArrayList<Student>()
    var teachers = ArrayList<Teacher>()
    val LOG_TAG: String = "MainPresenter_LOG"

    init {
        studentModelListener =  UserDataModel(this)
    }

    override fun OnCreate() {
        studentModelListener.loadAllFromDatabase().subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread()).subscribe{ list -> entitiesList.addAll(list)}
    }

    override fun onDestroy() {
        entitiesList.clear()
    }

    override fun getStudentSet(): ArrayList<Student> {
        entitiesList.forEach{
            if(it is DataEntities.StudentEntity){
                var s: Student = Student(it.id,it.name,it.gpa)
                students.add(s)
            }
        }
        return students


    }
    override fun getTeacherSet(): ArrayList<Teacher> {

        for ( i in entitiesList){
            if(i is DataEntities.TeacherEntity){
                var t : Teacher = Teacher(i.id,i.name,i.salary,i.course)
                teachers.add(t)
            }
        }
        return teachers
    }

    fun saveStudent(name: String, id: String, gpa: String){
        entitiesList.clear()
        if(studentValidation(name,id, gpa)){
            studentModelListener.addStudent(DataEntities.StudentEntity(id.toInt(),name,gpa.toDouble()))
            Log.d(LOG_TAG,"Student_Inserted_in_DB")
            students.add(Student(id.toInt(),name,gpa.toDouble()))
        }
    }
    fun saveTeacher(name: String,id: String,salary: String,course: String){
        entitiesList.clear()
        if(teacherValidation(name, id, salary, course)){
            studentModelListener.addTeacher(DataEntities.TeacherEntity(id.toInt(),name,salary.toInt(),course))
            Log.d(LOG_TAG,"teacher_Inserted_in_DB")
            teachers.add(Teacher(id.toInt(),name,salary.toInt(),course))
        }
    }



    fun studentValidation(name: String, id: String, gpa: String): Boolean{
        if(name.isEmpty()){
            Toast.makeText(view.getContext(),"Add name",Toast.LENGTH_SHORT)
            return false
        }
        if(id.isEmpty()){
            Toast.makeText(view.getContext(),"Add id",Toast.LENGTH_SHORT)
            return false
        }
        if(gpa.isEmpty()){
            Toast.makeText(view.getContext(),"Add gpa",Toast.LENGTH_SHORT)
            return false
        }
        return true
    }
    fun teacherValidation(name: String, id: String, salary: String,course: String): Boolean{
        if(name.isEmpty()){
            Toast.makeText(view.getContext(),"Add name",Toast.LENGTH_SHORT)
            return false
        }
        if(id.isEmpty()){
            Toast.makeText(view.getContext(),"Add id",Toast.LENGTH_SHORT)
            return false
        }
        if(salary.isEmpty()){
            Toast.makeText(view.getContext(),"Add gpa",Toast.LENGTH_SHORT)
            return false
        }
        if(course.isEmpty()){
            Toast.makeText(view.getContext(),"Add gpa",Toast.LENGTH_SHORT)
            return false
        }
        return true
    }

    fun onResume(){
        entitiesList.clear()
    }



}