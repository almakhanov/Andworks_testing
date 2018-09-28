package com.example.qalqa.actorslist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.actor_list.view.*

class ListAdapter(var actors:ArrayList<Actor>):RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val inflater = LayoutInflater.from(p0.context)
        val view = inflater.inflate(R.layout.actor_list, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return actors.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.name.text = actors[p1].name
        p0.film.text = actors[p1].film
        p0.image.setImageResource(actors[p1].image)
    }

    inner class ViewHolder(itemView: View?):RecyclerView.ViewHolder(itemView!!){
        var name = itemView!!.actor_name
        var film = itemView!!.actor_film
        var image = itemView!!.actor_image
    }
}