package com.qwer.secondintranet.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.qwer.firstintranet.Student
import com.qwer.secondintranet.R
import com.qwer.secondintranet.adapters.StudentAdapter
import com.qwer.secondintranet.activities.CreateFragmentListener
import com.qwer.secondintranet.activities.MainActivity
import com.qwer.secondintranet.activities.MainActivity.Companion.TAG
import kotlinx.android.synthetic.main.fragment_student_recycler.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class StudentRecyclerFragment : Fragment(), RecyclerListener {



    private var param1: String? = null
    private var param2: String? = null
    private var handler: CreateFragmentListener? = null
    private lateinit var createStudentFragment: CreateStudentFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_student_recycler, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //listener?.studentRecyclerFragmentMethod()
        recyclerShow()
    }

    override fun onResume() {
        super.onResume()
        fabAddStudent.setOnClickListener{
            createStudentFragment = CreateStudentFragment.newInstance("","")
            handler?.createFragment(createStudentFragment, R.id.firstFragmentContainer)
        }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        handler = context as MainActivity
    }


    override fun onStop() {
        super.onStop()
        Log.d("accepted", "studentRecyclerFragment STOPPPED")
        //onDestroy()
    }


    override fun onDetach() {
        super.onDetach()
    }

    interface StudentRecyclerFragmentListener {

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                StudentRecyclerFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

    private fun recyclerShow(){
        var layout = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
        RecView?.layoutManager = layout
        var adapter = StudentAdapter(MainActivity.list, this)
        RecView?.adapter = adapter
        //adapter.notifyDataSetChanged()

        if(adapter == null){
            Log.d("accepted", "RecyclerView: no adapter attached")
        }else if(layout == null){
            Log.d("accepted", "RecyclerView: no layout attached")
        }else{
            Log.d("accepted", "RecyclerView: adapter and layout is attached")
        }

    }


    override fun openProfile(s: Student) {
        Log.d(TAG, s.toString())

        Log.d(TAG, "openProfile DOING...")

        handler?.createFragment(s)

        Log.d(TAG, "openProfile DONE!!!")
    }



}

interface RecyclerListener{
    fun openProfile(s: Student)
}

