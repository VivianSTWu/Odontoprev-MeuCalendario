package com.example.meucalendario

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.meucalendario.databinding.ListItemBinding

class EventsAdapter(
    private var eventList: List<DataEvents>,
    private val listener: OnEventClickListener
) : RecyclerView.Adapter<EventsAdapter.ViewHolder>() {

    interface OnEventClickListener {
        fun onEventClick(event: DataEvents)
    }

    inner class ViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(event: DataEvents) {
            binding.apply {
                eventDay.text = event.date.toString()
                eventDescription.text = event.description
                root.setOnClickListener {
                    listener.onEventClick(event)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(eventList[position])
    }

    fun setEvents(events: List<DataEvents>) {
        eventList = events
        notifyDataSetChanged()
    }
}
