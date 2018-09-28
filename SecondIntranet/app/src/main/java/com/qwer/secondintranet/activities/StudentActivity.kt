package com.qwer.secondintranet.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.qwer.secondintranet.R
import com.qwer.secondintranet.database.StudentPreference.Companion.getStudSharedPref

class StudentActivity : AppCompatActivity() {

//    companion object {
//        var list = ArrayList<Student>()
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)
//        var stf = StudentPreference(this)

        getStudSharedPref()
//        Log.d("accepted", "list: ${list}")
//        Log.d("accepted", "listSize: ${list.size}")

//        val createStudBtn = findViewById<Button>(R.id.createBtn)
//        val showStudBtn = findViewById<Button>(R.id.showStudents)
//        val clearStudBtn = findViewById<Button>(R.id.clearStBtn)

//        createStudBtn.setOnClickListener{
//            createStud()
//        }
//        showStudBtn.setOnClickListener{
//            showStud()
//        }
//        clearStudBtn.setOnClickListener{
//            clearStud()
//        }



    }

    private fun createStud(){
        val intent = Intent(this, StudentCreateActivity::class.java)
        startActivity(intent)
    }

    private fun showStud(){
        val intent = Intent(this, StudentShowActivity::class.java)
        startActivity(intent)
    }

//    private fun clearStud(){
//        list = ArrayList()
//        StudentPreference.putStudSharedPref()
//        Toast.makeText(this,"Cleared!", Toast.LENGTH_SHORT).show()
//    }
}
