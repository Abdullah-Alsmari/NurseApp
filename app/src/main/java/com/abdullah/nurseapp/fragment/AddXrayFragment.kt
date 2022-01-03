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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.abdullah.nurseapp.viewmodel.AddTaskViewModel
import java.io.IOException


class AddXrayFragment : Fragment() {

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

    private fun observeData() {
        viewModel.showProgressDialog.observe(
            viewLifecycleOwner,
            Observer { shouldShow: Boolean ->
                if (shouldShow) {
                    progressDialog.show()
                }else{
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
/*
    private fun addUserChangeListenerList() {
        // User data change listener
        val list = mutableListOf<AddTaskModel>()
        databaseReference!!.child(emailId).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.getChildren()) {
                    val university: AddTaskModel = postSnapshot.getValue(AddTaskModel::class.java)!!
                    list.add(university)
//                    postSnapshot.ref.removeValue()

                }


            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.e("failed--", error.details.toString())
            }
        })
    }
*/

    private fun initProgressDialog() {
        progressDialog = ProgressDialog(requireActivity())
        progressDialog.setCancelable(false)
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("Uploading document...")
    }
}