package com.example.acer.intranetwithmvp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.acer.intranetwithmvp.Data.OnFragmentInteractionListener
import com.example.acer.intranetwithmvp.Presenters.MainPresenter

import kotlinx.android.synthetic.main.activity_splash.*

class Splash : AppCompatActivity() {
    var TIME_OUT: Long = 200
    override fun onCreate(savedInstanceState: Bundle?) {
        var presenter = MainPresenter(MainActivity())
        super.onCreate(savedInstanceState)
        Log.d("SPLASH","I am splash")
        setContentView(R.layout.activity_splash)
        presenter.OnCreate()
        Handler().postDelayed({
            run {
                var intent: Intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, TIME_OUT)

    }

}
