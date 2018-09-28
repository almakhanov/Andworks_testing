//package com.qwer.secondintranet
//
//import android.support.v7.widget.RecyclerView
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//
//
//class GenerealAdapter(var list : ArrayList<GenList>) : RecyclerView.Adapter<MainViewHolder>() {
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
//
//
//        val v = LayoutInflater.from(parent.context).inflate(R.layout.general_list_layout, parent, false)
//        return MainViewHolder(v)
//    }
//
//    override fun getItemCount(): Int {
//        return list.size
//    }
//
//    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
//        holder.bind(list[position])
//
////        holder.itemView.setOnClickListener {
////            for (i in 0 until list.size){
////                if(holder.itemView.title.text.equals("Students")){
////                    Log.d("accepted", "Student Opened!!!")
////
////                }
////            }
////        }
//    }
//
//}
//
//class MainViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
//    fun bind(g : GenList){
//        val viewTitle = itemView.findViewById<TextView>(R.id.title)
//        val viewCnt = itemView.findViewById<TextView>(R.id.amount)
//
//        viewTitle.text = g.title
//        viewCnt.text = g.amount.toString()
//    }
//}