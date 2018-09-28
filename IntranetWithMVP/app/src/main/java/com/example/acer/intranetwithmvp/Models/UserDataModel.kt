package com.example.acer.intranetwithmvp.Models

import android.util.Log
import com.example.acer.intranetwithmvp.Data.DataEntities
import com.example.acer.intranetwithmvp.Data.MyApp
import com.example.acer.intranetwithmvp.Data.Student
import com.example.acer.intranetwithmvp.Presenters.MainPresenter
import com.example.acer.intranetwithmvp.Presenters.StudentPresenterListener
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
class UserDataModel(val presenter: StudentPresenterListener): StudentModelListener  {


    override fun addTeacher(t: DataEntities.TeacherEntity) {
        Single.fromCallable {
            MyApp.database!!.studentDao().insertTeacher(t)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()
    }

//    override fun getCourses() {
//        MyApp.database!!.studentDao().getAllTeachers()?.subscribeOn(Schedulers.io())
//                ?.observeOn(AndroidSchedulers.mainThread())
//                ?.subscribe { listOfPeople -> listOfPeople.let{
//                    presenter.getTeacherSet(listOfPeople)
//                }
//                }
//    }

    override fun addCourse(c: DataEntities.CourseEntity) {
        Single.fromCallable {
            MyApp.database!!.studentDao().insertCourse(c)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()    }



    override fun addStudent(s: DataEntities.StudentEntity) {
        Single.fromCallable {
            MyApp.database!!.studentDao().insertStudent(s)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()
    }

    override fun loadAllFromDatabase(): Flowable<ArrayList<Any>> {
        var dataList: ArrayList<Any> = ArrayList()
        var flowable: Flowable<ArrayList<Any>> = Flowable.zip(
                MyApp.database!!.studentDao().getAllTeachers(),
                MyApp.database!!.studentDao().getAll(),
                BiFunction { t1, t2 ->
                    dataList.addAll(t1)
                    dataList.addAll(t2)
                    return@BiFunction dataList
                })
           return flowable
    }
}

