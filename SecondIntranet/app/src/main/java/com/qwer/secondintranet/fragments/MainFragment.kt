package com.qwer.secondintranet.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import com.qwer.secondintranet.R
import com.qwer.secondintranet.activities.CreateFragmentListener
import com.qwer.secondintranet.activities.MainActivity
import com.qwer.secondintranet.activities.MainActivity.Companion.TAG
import com.qwer.secondintranet.activities.MainActivity.Companion.list
import com.qwer.secondintranet.database.StudentPreference
import kotlinx.android.synthetic.main.fragment_main.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MainFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private var callback : CreateFragmentListener? = null
    private lateinit var createStudentFragment: CreateStudentFragment
    private lateinit var studentRecyclerFragment: StudentRecyclerFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }



    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        amountStudent.text = list.size.toString()

        studentActionMenu.setOnClickListener{
            studentActionMenuFunc()
        }




    }

    private fun studentActionMenuFunc(){
        var popupMenu = PopupMenu(activity, studentActionMenu)
        popupMenu.menuInflater.inflate(R.menu.main_cardview_option_menu, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.mainCardViewCreate -> {
                    Log.d(TAG, "createStudentFragment")

                    callback?.createFragment(createStudentFragment, R.id.firstFragmentContainer)
                    true
                }
                R.id.mainCardViewShowList -> {
                    Log.d(TAG, "studentRecyclerFragment")

                    callback?.createFragment(studentRecyclerFragment, R.id.firstFragmentContainer)
                    true
                }
                R.id.mainCardViewClearList -> {
                    Log.d(TAG, "clearStudentList")
                    clearStudentList()
                    true
                }
                else -> false
            }
        }

        popupMenu.show()
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as MainActivity
        createStudentFragment = CreateStudentFragment.newInstance("","")
        studentRecyclerFragment = StudentRecyclerFragment.newInstance("", "")
    }

    override fun onDetach() {
        super.onDetach()
        callback = null
        activity?.finish()
    }

    interface MainFragmentable{

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                MainFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

    private fun clearStudentList() {
        Log.d(TAG, "method clear Student")
        MainActivity.list = ArrayList()
        StudentPreference.putStudSharedPref()
        Toast.makeText(activity,"Cleared!", Toast.LENGTH_SHORT)
    }
}


