package com.qwer.secondintranet.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.qwer.secondintranet.R
import com.qwer.secondintranet.activities.MainActivity.Companion.list

class StudentShowActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_show)

        println(list.toString())

//        RecView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
//
//        RecView.adapter = StudentAdapter(list)
    }



}
