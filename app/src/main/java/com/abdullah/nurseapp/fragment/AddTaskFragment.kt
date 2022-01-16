package com.abdullah.nurseapp.fragment

import android.app.Activity.RESULT_OK
import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.abdullah.nurseapp.databinding.FragmentAddXrayBinding
import android.content.Intent

import android.provider.MediaStore

import android.net.Uri
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.abdullah.nurseapp.R
import com.abdullah.nurseapp.utils.setToolbarTitleWithBackButton
import com.abdullah.nurseapp.viewmodel.AddTaskViewModel
import java.io.IOException


class AddTaskFragment : Fragment() {

    private lateinit var binding: FragmentAddXrayBinding
    var FilePathUri: Uri? = null
    private lateinit var viewModel: AddTaskViewModel
    private lateinit var progressDialog: ProgressDialog


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddXrayBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "Add Details"
        viewModel = ViewModelProvider(this).get(AddTaskViewModel::class.java)
        binding.btnChooseImg.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(intent, "Select Document to upload"),
                50
            )
        }
        initProgressDialog()
        binding.btnSubmit.setOnClickListener {
            viewModel.uploadDataToFirebase(FilePathUri, binding)
        }
        observeData()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar(view)
    }

    private fun setUpToolbar(view: View) {
        val toolbar = view.findViewById<View>(com.abdullah.nurseapp.R.id.toolbar) as Toolbar
        setToolbarTitleWithBackButton(requireContext(), toolbar, resources.getString(R.string.addTask))
    }

    private fun observeData() {
        viewModel.showProgressDialog.observe(
            viewLifecycleOwner,
            Observer { shouldShow: Boolean ->
                if (shouldShow) {
                    progressDialog.show()
                } else {
                    progressDialog.dismiss()
                }
            })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 50 && resultCode == RESULT_OK && data != null && data.data != null) {
            FilePathUri = data.data
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(
                    requireActivity().getContentResolver(),
                    FilePathUri
                )
                binding.previewImage.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun initProgressDialog() {
        progressDialog = ProgressDialog(requireActivity())
        progressDialog.setCancelable(false)
        progressDialog.setTitle(R.string.dialog_wait)
        progressDialog.setMessage(R.string.Uploading_document.toString())
    }
}