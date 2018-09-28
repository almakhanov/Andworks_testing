package com.example.acer.intranetwithmvp.Data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(DataEntities.StudentEntity::class, DataEntities.TeacherEntity::class, DataEntities.CourseEntity::class, DataEntities.CourseStudents::class), version = 3) abstract class AppDatabase: RoomDatabase() {
    abstract fun studentDao(): StudentDao
}