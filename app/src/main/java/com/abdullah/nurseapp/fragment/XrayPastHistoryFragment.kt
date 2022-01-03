package com.abdullah.nurseapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.abdullah.nurseapp.adapters.XrayPastHistoryAdapter
import com.abdullah.nurseapp.databinding.FragmentXrayPastHistoryBinding
import com.abdullah.nurseapp.viewmodel.XrayHistoryViewModel

class XrayPastHistoryFragment : Fragment() {

    private lateinit var binding: FragmentXrayPastHistoryBinding
    private lateinit var adapter: XrayPastHistoryAdapter
    private lateinit var viewModel: XrayHistoryViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentXrayPastHistoryBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "Xray"
        viewModel = ViewModelProvider(this).get(XrayHistoryViewModel::class.java)
        observeData()
        setUpRecyclerview()
        viewModel.getXrayHistoryData()
        binding.fabXray.setOnClickListener {
            findNavController().navigate(XrayPastHistoryFragmentDirections.actionHistoryToAddFragment())
        }
        return binding.root
    }

    private fun observeData() {
        viewModel.xrayListData.observe(
            viewLifecycleOwner,
            Observer {
                adapter = XrayPastHistoryAdapter(requireContext(),it)
                binding.rvXrayHistory.adapter = adapter

            })

    }

    private fun setUpRecyclerview() {
        binding.rvXrayHistory.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false
        )
    }

}