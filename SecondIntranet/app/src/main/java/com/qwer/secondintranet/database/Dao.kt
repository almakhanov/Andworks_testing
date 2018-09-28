//package com.qwer.secondintranet.Database
//
//import android.arch.persistence.room.Dao
//import android.arch.persistence.room.Insert
//import android.arch.persistence.room.Query
//import io.reactivex.Flowable
//
//@Dao
//interface StudentDao {
//
//    @Query("SELECT * FROM student")
//    fun getAllStduents(): Flowable<ArrayList<User.Student>>
//
//    @Insert
//    fun insert(person: User.Student)
//}