package com.example.acer.intranetwithmvp.Views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import com.example.acer.intranetwithmvp.MainActivity
import com.example.acer.intranetwithmvp.OnSwipeTouchListener

import com.example.acer.intranetwithmvp.R
import com.example.acer.intranetwithmvp.UserAdapter
import kotlinx.android.synthetic.main.recycle_view_fragment.*
import kotlin.collections.ArrayList
import android.view.MotionEvent
import com.example.acer.intranetwithmvp.Data.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
const  val LOG_TAG = "RECYCLER_VIEW_LOG"

class RecycleViewFragment : Fragment(),RecyclerViewListener {


    private var param1: String? = null
    private var param2: String? = null
    lateinit var layout: GridLayoutManager
    lateinit var recyclerView: RecyclerView
    var viewStatus: Int? = null
    private var listener: OnFragmentInteractionListener? = null
    init {
        dataSet = ArrayList<Any>()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.recycle_view_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataSet?.clear()
        listener = context as MainActivity
        dataSet?.add(0,FH.Header(1))
        dataSet?.addAll(listener!!.getStudents())
        dataSet?.add(Student(3,"dfsdf",3.2))
        dataSet?.add(Student(3,"dfsdf",3.2))
        dataSet?.add(Student(3,"dfsdf",3.2))
        dataSet?.add(Student(3,"dfsdf",3.2))
        viewStatus = CURRENT_VIEW.STUDENT
        recyclerView = recList
        layout = GridLayoutManager (activity, 1)
        var adapter: UserAdapter = UserAdapter(dataSet!!)
        recyclerView.layoutManager = layout
        recyclerView.adapter = adapter
        addBtn.setOnClickListener{
            dataSet!!.clear()
            when(viewStatus){
                CURRENT_VIEW.STUDENT-> listener!!.startStudentCreateFragment()
                CURRENT_VIEW.TEACHER -> listener!!.startTeacherCreateFragment()
                CURRENT_VIEW.COURSE -> listener!!.startCourseCreateFragment()
            }
            Log.d(LOG_TAG,"$dataSet")
        }
        studentsList.setOnClickListener{
            showStudents()
        }
        teachersList.setOnClickListener{
            showTeachers()
        }
        CourseList.setOnClickListener{
            showCourses()
        }
//        val gesture: GestureDetector = GestureDetector(activity,
//                object : GestureDetector.SimpleOnGestureListener() {
//
//                    override fun onDown(e: MotionEvent): Boolean {
//                        return true
//                    }
//                    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
//                        Log.d(LOG_TAG, "onFling has been called!")
//                        val SWIPE_MIN_DISTANCE = 120
//                        val SWIPE_MAX_OFF_PATH = 250
//                        val SWIPE_THRESHOLD_VELOCITY = 200
//                        try {
//                            if (Math.abs(e1!!.getY() - e2!!.getY()) > SWIPE_MAX_OFF_PATH)
//                                return false
//                            if (e1!!.getX() - e2!!.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
//                                    when(viewStatus){
//                                        CURRENT_VIEW.STUDENT-> showTeachers()
//                                        CURRENT_VIEW.COURSE -> showStudents()
//                                    }
//                            } else if (e2!!.getX() - e1!!.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
//                                    when(viewStatus){
//
//                                        CURRENT_VIEW.STUDENT-> showCourses()
//                                        CURRENT_VIEW.TEACHER -> showStudents()
//                                    }
//
//                            }
//                        } catch (e: Exception) {
//                            // nothing
//                        }
//
//                        return super.onFling(e1, e2, velocityX, velocityY)
//                    }
//                })
//
//        parentLayout.setOnTouchListener(object: View.OnTouchListener{
//
//            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
//                gesture.onTouchEvent(p1)
//                return true
//            }
//
//        })
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onPause() {
        dataSet?.clear()
        listener!!.onResumeData()
        super.onPause()
    }
    override fun onDestroyView() {

        dataSet?.clear()
        super.onDestroyView()
    }

    override fun onDetach() {
        dataSet?.clear()
        super.onDetach()
        listener = null
    }

    fun showStudents(){
        dataSet?.clear()
        dataSet?.add(0,FH.Header(1))
        dataSet?.addAll(listener!!.getStudents())
        Log.d("Main_LOG","dataset "+ dataSet?.size.toString())
        viewStatus = CURRENT_VIEW.STUDENT
        var adapter: UserAdapter = UserAdapter(dataSet!!)
        recyclerView.layoutManager = layout
        recyclerView.adapter = adapter
    }
    fun showTeachers(){
        dataSet?.clear()
        dataSet?.add(0,FH.Header(2))
        dataSet?.addAll(listener!!.getTeachers())
        viewStatus = CURRENT_VIEW.TEACHER
        var adapter: UserAdapter = UserAdapter(dataSet!!)
        recyclerView.layoutManager = layout
        recyclerView.adapter = adapter
    }
    fun showCourses(){
        dataSet?.clear()
        dataSet?.add(0,FH.Header(3))
        dataSet?.addAll(listener!!.getCourse())
        viewStatus = CURRENT_VIEW.COURSE
        var adapter: UserAdapter = UserAdapter(dataSet!!)
        recyclerView.layoutManager = layout
        recyclerView.adapter = adapter
    }

    companion object {
     var dataSet: ArrayList<Any>? = ArrayList()

     @JvmStatic
        fun newInstance(param1: String, param2: String) =
                RecycleViewFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
    object CURRENT_VIEW{
        const val STUDENT = 1
        const val TEACHER = 2
        const val COURSE = 3
    }
    object Swipes{
        const val SWIPE_UP = 1
        const val SWIPE_DOWN = 2
        const val SWIPE_LEFT = 3
        const val SWIPE_RIGHT = 4
    }
}
