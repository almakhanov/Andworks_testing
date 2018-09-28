package com.example.acer.intranetwithmvp.Data

object FH {
    class Header(private var type: Int){
        fun getType(): Int{
            return type
        }
    }
    class Footer()
}