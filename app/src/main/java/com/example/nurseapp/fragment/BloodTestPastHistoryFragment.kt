package com.example.nurseapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.abdullah.nurseapp.databinding.FragmentBloodTestPastHistoryBinding
import com.abdullah.nurseapp.databinding.FragmentHomeBinding
import com.example.nurseapp.adapters.HomeGridAdapter
import com.example.nurseapp.model.HomeModel

class BloodTestPastHistoryFragment : Fragment(){

    private lateinit var binding : FragmentBloodTestPastHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBloodTestPastHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "BloodTest"

    }

}