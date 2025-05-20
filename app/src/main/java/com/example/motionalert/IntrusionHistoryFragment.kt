package com.example.motionalert

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.motionalert.databinding.FragmentIntrusionHistoryBinding

class IntrusionHistoryFragment : Fragment() {

    private var _binding: FragmentIntrusionHistoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: IntrusionEventAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIntrusionHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val events = StorageHelper.getEvents(requireContext()).reversed() // mới nhất lên trên

        adapter = IntrusionEventAdapter(events)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        binding.btnClear.setOnClickListener {
            StorageHelper.clearEvents(requireContext())
            adapter.updateEvents(emptyList())
            Toast.makeText(requireContext(), "Đã xóa lịch sử", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
