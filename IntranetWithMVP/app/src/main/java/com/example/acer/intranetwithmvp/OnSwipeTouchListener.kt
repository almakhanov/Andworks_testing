package com.example.acer.intranetwithmvp

import android.content.Context
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener

open class OnSwipeTouchListener(ctx: Context) : OnTouchListener {
    override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
        return true
    }

    private val gestureDetector: GestureDetector

    init {
        gestureDetector = GestureDetector(ctx, GestureListener())
    }

    private inner class GestureListener : SimpleOnGestureListener() {

        override fun onDown(e: MotionEvent): Boolean {
            return true
        }

        override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
            var result = false
            try {
                val diffY = e2.y - e1.y
                val diffX = e2.x - e1.x
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE.SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE.SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            onSwipeRight()
                        } else {
                            onSwipeLeft()
                        }
                    }
                    result = true
                } else if (Math.abs(diffY) > SWIPE.SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE.SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        onSwipeBottom()
                    } else {
                        onSwipeTop()
                    }
                }
                result = true

            } catch (exception: Exception) {
                exception.printStackTrace()
            }

            return result
        }


    }
    object SWIPE{

         val SWIPE_THRESHOLD = 100
         val SWIPE_VELOCITY_THRESHOLD = 100
    }
    fun onSwipeRight() {}

    fun onSwipeLeft() {}

    fun onSwipeTop() {}

    fun onSwipeBottom() {}
}