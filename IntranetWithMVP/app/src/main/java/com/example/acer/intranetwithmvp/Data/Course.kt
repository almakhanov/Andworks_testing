package com.example.acer.intranetwithmvp.Data

class Course (var id: Int?,var title: String, var description: String,var credit: Int,var teacher: Teacher){
    override fun toString(): String{
        return "sdfsdf ${id} ${title} ${teacher.getName()}"
    }
}