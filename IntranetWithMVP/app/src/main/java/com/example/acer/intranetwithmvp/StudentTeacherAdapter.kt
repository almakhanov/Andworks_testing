package com.example.acer.intranetwithmvp

import android.service.autofill.Dataset
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.TextView
import com.example.acer.intranetwithmvp.Data.*

class UserAdapter(val dataset: ArrayList<Any>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int =
            when(dataset[position]){
                is Student-> UserTypes.STUDENT
                is Teacher-> UserTypes.TEACHER
                is FH.Header -> when((dataset[position] as FH.Header).getType()){
                    1 -> UserTypes.STUDENT_HEADER
                    2 -> UserTypes.TEACHER_HEADER
                    else -> {UserTypes.COURSE_HEADER}
                }
                else ->{UserTypes.COURSE }
            }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d("asdfg","ViewType is $viewType")
        when(viewType){
            UserTypes.COURSE -> return CourseViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_course,parent,false))
            UserTypes.STUDENT -> return StudentViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_student,parent,false))
            UserTypes.TEACHER_HEADER -> return HeaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.teacher_item_header,parent,false))
            UserTypes.STUDENT_HEADER -> return HeaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.student_item_header,parent,false))
            UserTypes.COURSE_HEADER -> return HeaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.course_item_header,parent,false))

            else -> {
                return TeacherViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_teacher,parent,false))
            }
        }

    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("ADAPTER_LOG","${dataset[position].toString()}")

        when(holder){
            is StudentViewHolder -> {
                return holder.bind(dataset[position] as Student)}
            is TeacherViewHolder -> return holder.bind(dataset[position] as Teacher)
            is CourseViewHolder -> return holder.bind(dataset[position] as Course)

        }
    }


    inner class StudentViewHolder(v: View): RecyclerView.ViewHolder(v){

        fun bind(p: Student){
            val name = itemView.findViewById<TextView>(R.id.name)
            val age  = itemView.findViewById<TextView>(R.id.age)
            val gpa  = itemView.findViewById<TextView>(R.id.gpa)

            name.text=p.getName()
            age.text =p.getId().toString()
            gpa.text = p.gpa!!.toString()

            Log.d("qwerty", "vieewHolder => $p")
        }

    }
    inner class TeacherViewHolder(v: View): RecyclerView.ViewHolder(v){
        fun bind(p: Teacher){
            val name = itemView.findViewById<TextView>(R.id.name)
            val age  = itemView.findViewById<TextView>(R.id.age)
            val salary  = itemView.findViewById<TextView>(R.id.gpa)

            name.text=p.getName()
            age.text =p.getId().toString()
            salary.text = p.salary.toString()

            Log.d("qwerty", "vieewHolder => $p")
        }
    }
    inner class CourseViewHolder(v: View): RecyclerView.ViewHolder(v){
        var  btn = itemView.findViewById<Button>(R.id.btn2)
        fun bind(p: Course){
            val title = itemView.findViewById<TextView>(R.id.title)
            val id  = itemView.findViewById<TextView>(R.id.id)
            val description  = itemView.findViewById<TextView>(R.id.description)
            var teacherName = itemView.findViewById<TextView>(R.id.teacherName)

            title.text=p.title.toString()
            id.text =p.id.toString()
            description.text = p.description.toString()
            teacherName.text = p.teacher.getName()
            btn.setOnClickListener{
                Log.d("qwerty","adapterBtn")
            }
            Log.d("qwerty", "vieewHolder => $p")
        }

    }
    inner class HeaderViewHolder(v: View): RecyclerView.ViewHolder(v){}

    object UserTypes{
        const val TEACHER = 0
        const val STUDENT = 1
        const val COURSE = 2
        const val STUDENT_HEADER = 3
        const val TEACHER_HEADER = 4
        const val COURSE_HEADER  = 5
    }
}
