package com.example.motionalert

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.motionalert.databinding.ItemIntrusionEventBinding

class IntrusionEventAdapter(
    private var events: List<IntrusionEvent>
) : RecyclerView.Adapter<IntrusionEventAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemIntrusionEventBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(event: IntrusionEvent) {
            binding.txtMessage.text = event.message
            binding.txtMac.text = "Nguồn: ${event.mac}"
            binding.txtTimestamp.text = event.timestamp
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemIntrusionEventBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount() = events.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(events[position])
    }

    fun updateEvents(newEvents: List<IntrusionEvent>) {
        events = newEvents
        notifyDataSetChanged()
    }
}
