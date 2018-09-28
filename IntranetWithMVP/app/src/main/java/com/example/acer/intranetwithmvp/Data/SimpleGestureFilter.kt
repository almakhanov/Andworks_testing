package com.example.acer.intranetwithmvp.Data

import android.app.Activity
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import com.example.acer.intranetwithmvp.Views.MainViewListener
import android.R.attr.mode
import android.text.method.Touch.onTouchEvent
import android.R.attr.mode
import com.example.acer.intranetwithmvp.MainActivity


class SimpleGestureFilter(var view: MainActivity,var listener: SimpleGestureListener): SimpleOnGestureListener(){

    init {

    }

     val swipe_Min_Distance = 100
     val swipe_Max_Distance = 350
     val swipe_Min_Velocity = 100

     val mode = Modes.MODE_DYNAMIC
     val running = true
     var tapIndicator = false


     val context: Activity? = null
     val detector: GestureDetector? = GestureDetector(view,this)


    fun onTouchEvent(event: MotionEvent) {

        if (!this.running)
            return

        val result = this.detector!!.onTouchEvent(event)
        // Get the gesture
        if (this.mode === Modes.MODE_SOLID)
            event.action = MotionEvent.ACTION_CANCEL
        else if (this.mode === Modes.MODE_DYNAMIC) {

            if (event.action == Modes.ACTION_FAKE)
                event.action = MotionEvent.ACTION_UP
            else if (result)
                event.action = MotionEvent.ACTION_CANCEL
            else if (this.tapIndicator) {
                event.action = MotionEvent.ACTION_DOWN
                this.tapIndicator = false
            }

        }
        // else just do nothing, it's Transparent
    }

    override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float,
                         velocityY: Float): Boolean {
        var velocityX = velocityX
        var velocityY = velocityY

        val xDistance = Math.abs(e1.x - e2.x)
        val yDistance = Math.abs(e1.y - e2.y)

        if (xDistance > this.swipe_Max_Distance || yDistance > this.swipe_Max_Distance)
            return false

        velocityX = Math.abs(velocityX)
        velocityY = Math.abs(velocityY)
        var result = false

        if (velocityX > this.swipe_Min_Velocity && xDistance > this.swipe_Min_Distance) {
            if (e1.x > e2.x)
            // right to left
                this.listener?.onSwipe(Swipes.SWIPE_LEFT)
            else
                this.listener?.onSwipe(Swipes.SWIPE_RIGHT)

            result = true
        }
        return result
    }

    override fun onSingleTapUp(e: MotionEvent): Boolean {
        this.tapIndicator = true
        return false
    }



    override fun onDoubleTapEvent(arg: MotionEvent): Boolean {
        return true
    }

    override fun onSingleTapConfirmed(arg: MotionEvent): Boolean {

        if (this.mode === Modes.MODE_DYNAMIC) { // we owe an ACTION_UP, so we fake an
            arg.action = Modes.ACTION_FAKE // action which will be converted to an
            // ACTION_UP later.
            this.context?.dispatchTouchEvent(arg)
        }

        return false
    }





    object Modes{
        const val MODE_TRANSPARENT = 0
        const val MODE_SOLID = 1
        const val MODE_DYNAMIC = 2
        const val ACTION_FAKE = -13
    }
    object Swipes{
        const val SWIPE_UP = 1
        const val SWIPE_DOWN = 2
        const val SWIPE_LEFT = 3
        const val SWIPE_RIGHT = 4
    }
}

 interface SimpleGestureListener {
    fun onSwipe(direction: Int)

    fun onDoubleTap()
}
