package com.qwer.firstintranet

class Student : Person{
    open var gpa : Double

    init {
        this.gpa = 0.0
    }

    constructor(name_ : String, age_ : Int, gpa_ : Double) : super(name_, age_){
        gpa = gpa_
    }

    constructor() : super(){
        gpa = 0.0
    }

    fun getterGpa() : Double {
        return gpa
    }

    override fun toString(): String {
        return "Student(gpa=$gpa) ${super.toString()}"
    }


}
