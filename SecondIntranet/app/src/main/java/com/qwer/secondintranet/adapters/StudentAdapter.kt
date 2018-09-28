package com.qwer.secondintranet.adapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.qwer.firstintranet.Student
import com.qwer.secondintranet.R
import com.qwer.secondintranet.activities.MainActivity.Companion.TAG
import com.qwer.secondintranet.fragments.RecyclerListener


class StudentAdapter(var list : ArrayList<Student>, var recyclerListener: RecyclerListener) : RecyclerView.Adapter<StudentAdapter.StudentsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentsViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.student_layout, parent, false)
        return StudentsViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: StudentsViewHolder, position: Int) {
        holder.bind(list[position])
    }


    inner class StudentsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        fun bind(s : Student){
            val viewName = itemView.findViewById<TextView>(R.id.textViewName)
            val viewAge = itemView.findViewById<TextView>(R.id.textViewAge)
            val viewGpa = itemView.findViewById<TextView>(R.id.textViewGpa)

            viewName.text = s.getterName()
            viewAge.text = s.getterAge().toString()
            viewGpa.text = s.getterGpa().toString()

            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            var pos : Int = adapterPosition
            var s : Student = list[pos]
            Log.d(TAG, "OnClicked")

            recyclerListener.openProfile(s)

        }

    }
}

