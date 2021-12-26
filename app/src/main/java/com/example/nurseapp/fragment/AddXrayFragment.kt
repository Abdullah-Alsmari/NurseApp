package com.example.nurseapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.abdullah.nurseapp.databinding.FragmentAddXrayBinding
import com.abdullah.nurseapp.databinding.FragmentHomeBinding
import com.abdullah.nurseapp.databinding.FragmentXrayPastHistoryBinding
import com.example.nurseapp.adapters.HomeGridAdapter
import com.example.nurseapp.model.HomeModel

class AddXrayFragment : Fragment(){

    private lateinit var binding : FragmentAddXrayBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddXrayBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "Add Details"

        return binding.root
    }

}