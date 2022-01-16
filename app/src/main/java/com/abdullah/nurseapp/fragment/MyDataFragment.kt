package com.abdullah.nurseapp.fragment

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.abdullah.nurseapp.databinding.FragmentMyDataBinding
import com.abdullah.nurseapp.utils.*
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.abdullah.nurseapp.R
import com.abdullah.nurseapp.viewmodel.MyDataViewModel


class MyDataFragment : Fragment() {

    private lateinit var binding: FragmentMyDataBinding
    private lateinit var viewModel: MyDataViewModel
    private lateinit var progressDialog: ProgressDialog


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar(view)
        viewModel = ViewModelProvider(this).get(MyDataViewModel::class.java)
        binding.btnSubmit.setOnClickListener {
            viewModel.uploadDataToFirebase(binding)
        }
        viewModel.profileData(binding)
        observeData()
        initProgressDialog()
        val bloodGroupList = mutableListOf<String>()
        bloodGroupList.add("B+")
        bloodGroupList.add("O-")
        bloodGroupList.add("AB+")
        bloodGroupList.add("AB-")
        bloodGroupList.add("B-")
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireActivity(),
            android.R.layout.simple_dropdown_item_1line,
            bloodGroupList
        )
        binding.filledBloodGroupDropdown.setAdapter(adapter)
    }

    private fun observeData() {
        viewModel.showProgressDialog.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it) {
                progressDialog.show()
            } else {
                progressDialog.dismiss()
            }
        })

    }


    private fun setUpToolbar(view: View) {
        val toolbar = view.findViewById<View>(com.abdullah.nurseapp.R.id.toolbar) as Toolbar
        setToolbarTitleWithBackButton(requireContext(), toolbar,resources.getString(R.string.my_profile))
    }

    private fun initProgressDialog() {
        progressDialog = ProgressDialog(requireActivity())
        progressDialog.setCancelable(false)
        progressDialog.setTitle(R.string.dialog_wait)
        progressDialog.setMessage(R.string.dialog_Getting_details.toString())
    }


}