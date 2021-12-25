package com.example.nurseapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.abdullah.nurseapp.databinding.FragmentHomeBinding
import com.example.nurseapp.adapters.HomeGridAdapter
import com.example.nurseapp.model.HomeModel

class HomeFragment : Fragment(){

    private lateinit var binding : FragmentHomeBinding
    private lateinit var adapter: HomeGridAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val dataList = populateData()
        setupRecyclerView(dataList)
        return binding.root
    }

    private fun populateData() : List<HomeModel>{
        val list = mutableListOf<HomeModel>()
        list.add(HomeModel("X-ray"))
        list.add(HomeModel("Blood Testing"))
        return list

    }
    private fun setupRecyclerView(dataList : List<HomeModel>){
        adapter = HomeGridAdapter()
        binding.recyclerView.setHasFixedSize(true)
        adapter.submitList(dataList)
        binding.recyclerView.adapter = adapter

    }
}