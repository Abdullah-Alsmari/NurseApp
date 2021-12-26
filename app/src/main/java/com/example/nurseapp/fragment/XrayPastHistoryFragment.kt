package com.example.nurseapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.abdullah.nurseapp.databinding.FragmentHomeBinding
import com.abdullah.nurseapp.databinding.FragmentXrayPastHistoryBinding
import com.example.nurseapp.adapters.HomeGridAdapter
import com.example.nurseapp.model.HomeModel

class XrayPastHistoryFragment : Fragment() {

    private lateinit var binding: FragmentXrayPastHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentXrayPastHistoryBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "Xray"
        binding.fabXray.setOnClickListener {
            findNavController().navigate(XrayPastHistoryFragmentDirections.actionHistoryToAddFragment())
        }
        return binding.root
    }

}