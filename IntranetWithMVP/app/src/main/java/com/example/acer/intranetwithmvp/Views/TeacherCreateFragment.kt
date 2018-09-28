package com.example.acer.intranetwithmvp.Views

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.*
import com.example.acer.intranetwithmvp.Data.OnFragmentInteractionListener
import com.example.acer.intranetwithmvp.MainActivity

import com.example.acer.intranetwithmvp.R
import kotlinx.android.synthetic.main.fragment_student_create.*
import kotlinx.android.synthetic.main.fragment_teacher_create.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class TeacherCreateFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        listener = context as MainActivity
        return inflater.inflate(R.layout.fragment_teacher_create, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.create_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        Log.d("qwerty", "saving in process")
        Log.d("qwerty", tId.text.toString()+" Name "+ tName.text.toString()+ " Salary "+salary.text.toString()+" course "+ course.text.toString())

        when (item!!.itemId) {

            R.id.done_btn -> listener!!.addTeacher(tName.text.toString(),tId.text.toString(), salary.text.toString(),course.text.toString())

        }
        return super.onOptionsItemSelected(item)

    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }



    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                TeacherCreateFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
