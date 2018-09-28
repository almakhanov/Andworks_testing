package com.example.acer.intranetwithmvp.Data

open class User(private var id: Int?, private var name: String) {
    fun getName(): String{
        return name
    }
    fun getId(): Int{
        return id!!
    }
    fun setName(name: String){
        this.name = name
    }
    fun setId(age: Int){
        this.id = age
    }

    override fun toString(): String {
        return "this is ${name} he's age is ${id}"
    }
}