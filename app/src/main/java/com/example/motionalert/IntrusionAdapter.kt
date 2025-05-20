package com.example.motionalert

//package com.example.intrusionapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.motionalert.databinding.ItemIntrusionBinding

class IntrusionAdapter(private val events: List<IntrusionEvent>) :
    RecyclerView.Adapter<IntrusionAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemIntrusionBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemIntrusionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount() = events.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = events[position]
        holder.binding.messageText.text = event.message
        holder.binding.timeText.text = event.timestamp
    }
}
