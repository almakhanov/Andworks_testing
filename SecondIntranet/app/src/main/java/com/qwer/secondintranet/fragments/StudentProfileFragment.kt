package com.qwer.secondintranet.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qwer.firstintranet.Student
import com.qwer.secondintranet.R
import com.qwer.secondintranet.activities.MainActivity
import com.qwer.secondintranet.activities.MainActivity.Companion.TAG
import com.qwer.secondintranet.activities.MainActivity.Companion.staticStudent
import kotlinx.android.synthetic.main.fragment_student_profile.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class StudentProfileFragment : Fragment() {


    private var param1: String? = null
    private var param2: String? = null
    private var listener: StudentProfileFragmentListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        Log.d(TAG, "StudentProfile OnCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_student_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        stProfName.text = staticStudent?.getterName()
        stProfAge.text = staticStudent?.getterAge().toString()
        stProfGpa.text = staticStudent?.getterGpa().toString()

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is StudentProfileFragmentListener) {
            listener = context as MainActivity
        } else {
            throw RuntimeException(context.toString() + " must implement StudentProfileFragmentListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    fun openSetProfile(s: Student){
        Log.d(TAG, "openSetProfile : $s")
        stProfName.text = s.getterName()
        stProfAge.text = s.getterAge().toString()
        stProfGpa.text = s.getterGpa().toString()
    }

    interface StudentProfileFragmentListener {

    }



    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                StudentProfileFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }





}



