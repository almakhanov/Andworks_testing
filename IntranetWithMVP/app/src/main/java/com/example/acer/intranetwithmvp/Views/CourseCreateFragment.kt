package com.example.acer.intranetwithmvp.Views

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.acer.intranetwithmvp.Data.OnFragmentInteractionListener
import com.example.acer.intranetwithmvp.Data.Teacher
import com.example.acer.intranetwithmvp.MainActivity

import com.example.acer.intranetwithmvp.R
import kotlinx.android.synthetic.main.fragment_course_create.*
import kotlinx.android.synthetic.main.fragment_student_create.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class CourseCreateFragment : Fragment(),AdapterView.OnItemSelectedListener {


    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_course_create, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        listener = context as MainActivity
        var teachers = ArrayList<String>()
        listener?.getTeachers()?.forEach{
            Log.d("COURSE_CREATE","${it.getName()} id ${it.getId()}")
            var s: String = "${it.getName()} id ${it.getId()}"
            teachers.add(s)
        }
        var adapter: ArrayAdapter<String> = ArrayAdapter(activity,android.R.layout.simple_spinner_item,teachers)
        var s = spinner
        s.prompt = "Teachers"
        s.adapter = adapter
        super.onViewCreated(view, savedInstanceState)
    }
    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        Toast.makeText(activity,"position $p2, $p3",Toast.LENGTH_SHORT)
    }
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.create_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        Log.d("qwerty","saving in process")
        when(item!!.itemId){
            R.id.done_btn->listener!!.addStudent(sId.text.toString(), name.text.toString(),gpa.text.toString())

        }
        return super.onOptionsItemSelected(item)
    }
    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                CourseCreateFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
