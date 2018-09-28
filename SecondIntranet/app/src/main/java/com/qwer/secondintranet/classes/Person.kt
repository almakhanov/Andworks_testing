package com.qwer.firstintranet

abstract class Person(){

    private var name : String
    private var age : Int

    init{
        name = "NoName"
        age = -1
    }


    fun getterName() : String = name
    fun getterAge() : Int = age


    constructor(name_ : String, age_ : Int) : this() {
        name = name_
        age = age_
    }


    override fun toString(): String {
        return "Person(name='$name', age=$age)"
    }



}