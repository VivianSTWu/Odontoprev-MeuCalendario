package com.example.meucalendario

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.WindowInsetsCompat.Type
import androidx.recyclerview.widget.RecyclerView
import com.example.meucalendario.databinding.ListItemBinding

class EventsAdapter( val eventList : List<DataEvents>) : RecyclerView.Adapter<EventsAdapter.ViewHolder>() {

    inner class ViewHolder(binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val eventDate = binding.eventDay
        val eventDesc = binding.eventDescription
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding = ListItemBinding.inflate(layoutInflater, parent, false)

        return ViewHolder(binding)

        /*val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(itemView)*/
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = eventList[position]
        holder.eventDate.text = currentItem.date.toString()
        holder.eventDesc.text = currentItem.description
    }


}
