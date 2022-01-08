package com.abdullah.nurseapp.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.abdullah.nurseapp.R
import com.abdullah.nurseapp.adapters.XrayPastHistoryAdapter
import com.abdullah.nurseapp.databinding.FragmentBloodTestPastHistoryBinding
import com.abdullah.nurseapp.databinding.FragmentXrayPastHistoryBinding
import com.abdullah.nurseapp.model.AddTaskModel
import com.abdullah.nurseapp.utils.setToolbarTitleWithBackButton
import com.abdullah.nurseapp.viewmodel.BloodTestViewModel
import com.abdullah.nurseapp.viewmodel.XrayHistoryViewModel

class BloodTestPastHistoryFragment : Fragment() {

    private lateinit var binding: FragmentBloodTestPastHistoryBinding
    private lateinit var adapter: XrayPastHistoryAdapter
    private lateinit var viewModel: BloodTestViewModel


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
        setUpToolbar(view)
        viewModel = ViewModelProvider(this).get(BloodTestViewModel::class.java)
        observeData()
        setUpRecyclerview()
        viewModel.getBloodTestHistoryData()

    }

    private fun setUpToolbar(view: View) {
        val toolbar = view.findViewById<View>(R.id.toolbar) as Toolbar
        setToolbarTitleWithBackButton(requireContext(), toolbar, "Past Blood Test History")
    }

    private fun observeData() {
        viewModel.xrayListData.observe(
            viewLifecycleOwner,
            Observer {
                adapter = XrayPastHistoryAdapter(
                    requireContext(),
                    it,
                    object : XrayPastHistoryAdapter.OnItemClickListener {
                        override fun onItemClick(item: AddTaskModel?) {
                            findNavController().navigate(
                                XrayPastHistoryFragmentDirections.actionHistoryToFullScreenImageFragment(
                                    item!!.imgURL!!
                                )
                            )
                        }

                        override fun onDeleteClick(item: AddTaskModel?) {
                            viewModel.deleteItem(item!!.key)
                            viewModel.getBloodTestHistoryData()

                        }

                        override fun onShareClick(item: AddTaskModel?) {
                            val sendIntent = Intent()
                            sendIntent.action = Intent.ACTION_SEND
                            sendIntent.putExtra(
                                Intent.EXTRA_TEXT,
                                item!!.title
                            )
                            sendIntent.type = "text/plain"
                            startActivity(sendIntent)
                        }

                    })
                binding.rvBloodTestHistory.adapter = adapter

            })

    }

    private fun setUpRecyclerview() {
        binding.rvBloodTestHistory.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false
        )
    }

}