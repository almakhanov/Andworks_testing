package com.qwer.secondintranet.fragments

import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.qwer.firstintranet.Student
import com.qwer.secondintranet.R
import com.qwer.secondintranet.activities.MainActivity
import com.qwer.secondintranet.activities.MainActivity.Companion.TAG
import com.qwer.secondintranet.database.StudentPreference
import kotlinx.android.synthetic.main.fragment_create_student.*
import java.text.SimpleDateFormat
import java.util.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CreateStudentFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var listener: CreateStudenFragmentListener? = null
    private var calendar = Calendar.getInstance()
    private var dateSetListener : DatePickerDialog.OnDateSetListener? = null
    private var spinnerFac : Spinner? = null
    private var spinnerSpec : Spinner? = null
    private var spinnerYearOfStudy : Spinner? = null


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }



    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        //var
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create_student, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        birthDayBtn()


        saveBtn.setOnClickListener{
            var name : String = nameTxt.text.toString()
            var age : Int = ageTxt.text.toString().toInt()
            var gpa : Double = gpaTxt.text.toString().toDouble()

            var s = Student(name, age, gpa)
            saveStudent(s)

            nameTxt.setText("")
            ageTxt.setText("")
            gpaTxt.setText("")

        }
    }

    override fun onResume() {
        super.onResume()
        studentBirthdayInput.setOnClickListener{
            DatePickerDialog(activity, dateSetListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        spinnerFun()
    }

    private fun spinnerFun(){
        //Faculty spinner
        spinnerFac = this.studentFacultyInput
        spinnerFac?.adapter = ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, MainActivity.facultyList)
        spinnerFac?.prompt = "Select your Faculty"
        spinnerFac?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if(p2 == 0){
                    //Toast.makeText(activity, "Select other option please!", Toast.LENGTH_SHORT).show()
                }
                Log.d(TAG, "FacOption: ${MainActivity.facultyList[p2]}")
            }
        }

        //Specialization spinner
        spinnerSpec = this.studentSpecInput
        spinnerSpec?.adapter = ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, MainActivity.specList)
        spinnerSpec?.prompt = "Select your Specialization"
        spinnerSpec?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if(p2 == 0){
                    //Toast.makeText(activity, "Select other option please!", Toast.LENGTH_SHORT).show()
                }
                Log.d(TAG, "SpecOption: ${MainActivity.specList[p2]}")

            }
        }

        //YearOfStudy spinner
        spinnerYearOfStudy = this.studentYearOfStudyInput
        spinnerYearOfStudy?.adapter = ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, MainActivity.yearOfStudyList)
        spinnerYearOfStudy?.prompt = "Select your Year Of Study"
        spinnerYearOfStudy?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if(p2 == 0){
                    //Toast.makeText(activity, "Select other option please!", Toast.LENGTH_SHORT).show()
                }
                Log.d(TAG, "YearOfStudyOption: ${MainActivity.yearOfStudyList[p2]}")

            }
        }

    }

    private fun birthDayBtn(){
        dateSetListener = DatePickerDialog.OnDateSetListener{ view, year, monthOfYear, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val birthFormat = "dd.MM.yyyy"
            val simpleDateFormat = SimpleDateFormat(birthFormat, Locale.US)
            studentBirthdayInput.text = simpleDateFormat.format(calendar.time)

            Log.d(TAG, calendar.time.toString())

        }
    }



    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is CreateStudenFragmentListener) {
            listener = context as MainActivity
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface CreateStudenFragmentListener {

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                CreateStudentFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }


    private fun saveStudent(s : Student) {
        Log.d("accepted", "method saveStudent")
        MainActivity.list.add(s)
        StudentPreference.putStudSharedPref()
        Toast.makeText(activity,"saved(${MainActivity.list.size})", Toast.LENGTH_SHORT).show()

        //DB
//        var stud = User.Student(0, s.getterName(), s.getterAge(), s.getterGpa())
//        Single.fromCallable {
//            studentDB?.StudentDao()?.insert(stud)
//        }.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread()).subscribe()
    }




}
