package com.qwer.secondintranet.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qwer.secondintranet.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class StudentMainFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

//    private var callback : CreateFragmentListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_student_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        createBtn.setOnClickListener{
//            Log.d(TAG, "callback createStudent")
//            callback?.createFragment()
//        }
//
//        showStudents.setOnClickListener{
//            Log.d(TAG, "callback showStudentList")
//            callback?.showStudentList()
//        }

//        clearStBtn.setOnClickListener{
//            Log.d(TAG, "callback clearStudentList")
//            clearStudentList()
//        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        callback = context as MainActivity
    }

    override fun onDetach() {
        super.onDetach()
//        callback = null
    }



    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                StudentMainFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }


//    private fun clearStudentList() {
//        Log.d(TAG, "method clear Student")
//        MainActivity.list = ArrayList()
//        StudentPreference.putStudSharedPref()
//        Toast.makeText(activity,"Cleared!", Toast.LENGTH_SHORT)
//    }
}
