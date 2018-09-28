package com.example.acer.intranetwithmvp.Data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable


    @Dao
    interface StudentDao {
        @Query("SELECT*FROM Students")
        fun getAll(): Flowable<List<DataEntities.StudentEntity>>
        @Insert
        fun insertStudent(sE: DataEntities.StudentEntity)
        @Query("SELECT*FROM Teachers")
        fun getAllTeachers(): Flowable<List<DataEntities.TeacherEntity>>
        @Insert
        fun insertTeacher(tE: DataEntities.TeacherEntity)
        @Insert
        fun insertCourse(cE: DataEntities.CourseEntity)
    }
