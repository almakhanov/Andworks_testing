package com.example.acer.intranetwithmvp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.acer.intranetwithmvp.Data.*
import com.example.acer.intranetwithmvp.Presenters.MainPresenter
import com.example.acer.intranetwithmvp.Views.*
import android.text.method.Touch.onTouchEvent
import android.view.GestureDetector
import android.view.MotionEvent
import kotlinx.android.synthetic.main.recycle_view_fragment.*


class MainActivity : AppCompatActivity(),MainViewListener,OnFragmentInteractionListener,OnRecycleItemClickListener {


    companion object {
        var studentsArray: ArrayList<Student> = ArrayList()
        var teahersArray: ArrayList<Teacher> = ArrayList()
        var courses: ArrayList<Course> = ArrayList()


    }
    lateinit var mainPresenter: MainPresenter
    lateinit var detector: SimpleGestureFilter
    init {
        mainPresenter = MainPresenter(this)
        mainPresenter.getStudentSet()
        mainPresenter.getTeacherSet()


    }
    override fun getContext(): MainActivity {
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        Log.d("MAIN_ACTIVITY","OnCreate")
        var t: Teacher = Teacher(1,"Dauren",130000,"ads")
        courses.add(Course(1,"asd","asddsfdgsdgfdvcvsdgfvcbdfbcvbdfg",3,t))
        courses.add(Course(2,"asdf","asddsfdgsdgfdvcvsdgfvcbdfbcvbdfgsgfh",3,t))
        courses.add(Course(3,"asdf","asddsfdgsdgfdvcvsdgfvcbdfbcvbdfghfghbvb",3,t))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(R.id.container, RecycleViewFragment()).commit()

    }



    override fun addStudent(name: String, id: String, gpa: String) {
        if (mainPresenter.studentValidation(name,id,gpa)) {
            mainPresenter.saveStudent(name,id,gpa)
            //mainPresenter.getStudents()
            supportFragmentManager.beginTransaction().replace(R.id.container, RecycleViewFragment()).commit()
        }
    }

    override fun addTeacher(name: String, id: String, salary: String, course: String) {
        if (mainPresenter.teacherValidation(name,id,salary, course)) {
            mainPresenter.saveTeacher(name, id, salary, course)
            supportFragmentManager.beginTransaction().replace(R.id.container,RecycleViewFragment()).commit()
        }

    }

    override fun getStudents(): ArrayList<Student> {
        return mainPresenter.students
    }

    override fun getTeachers(): ArrayList<Teacher> {
        return mainPresenter.teachers
    }

    override fun getCourse(): ArrayList<Course> {
        return courses
    }

    override fun startStudentCreateFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.container, StudentCreateFragment()).addToBackStack("tag").commit()
    }
    override fun startTeacherCreateFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.container, TeacherCreateFragment()).addToBackStack("tag").commit()
    }
    override fun startCourseCreateFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.container, CourseCreateFragment()).addToBackStack("tag").commit()
    }

    override fun onResumeData() {
        mainPresenter.onResume()
    }
    override fun onDestroy() {
        mainPresenter.onDestroy()
        super.onDestroy()
    }

    override fun onStudentClicked(s : Student) {
        startActivity(Intent(this,StudentActivity::class.java))
    }

    override fun showToast(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
