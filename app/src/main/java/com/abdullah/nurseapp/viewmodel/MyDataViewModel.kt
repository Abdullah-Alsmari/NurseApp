package com.abdullah.nurseapp.viewmodel

import android.app.Application
import android.content.ContentResolver
import android.net.Uri
import android.util.Log
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abdullah.nurseapp.databinding.FragmentAddXrayBinding
import com.abdullah.nurseapp.databinding.FragmentMyDataBinding
import com.abdullah.nurseapp.model.AddTaskModel
import com.abdullah.nurseapp.model.MyDataModel
import com.abdullah.nurseapp.utils.USERNAME
import com.abdullah.nurseapp.utils.getStringFromPrefs
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class MyDataViewModel(application: Application) : AndroidViewModel(application) {



    private val _showProgressDialog = MutableLiveData<Boolean>(false)
    val showProgressDialog: LiveData<Boolean>
        get() = _showProgressDialog


    var storageReference: StorageReference? = null
    var databaseReference: DatabaseReference? = null
    var Database_Path = "KnownHealthConditionsDatabase"
    var userName = ""


    init {
        storageReference = FirebaseStorage.getInstance().reference;
        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);
        userName = getStringFromPrefs(getApplication(), USERNAME)!!

    }

    fun uploadDataToFirebase(binding: FragmentMyDataBinding) {
        addTask(binding)

    }


    private fun addTask(binding: FragmentMyDataBinding) {
        val user = MyDataModel(
            binding.edAge.text.toString(),
            binding.filledBloodGroupDropdown.text.toString(),
            binding.edCondition.text.toString()
        )
        databaseReference!!.child(userName).setValue(user)
        binding.edAge.setText("")
        binding.filledBloodGroupDropdown.setText("")
        binding.edCondition.setText("")
        Toast.makeText(getApplication(),"Data added Successfully!!!", Toast.LENGTH_LONG).show()

    }

    fun profileData(binding: FragmentMyDataBinding) {
        _showProgressDialog.value=true
        databaseReference!!.child(userName).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user = dataSnapshot.getValue(MyDataModel::class.java)
                _showProgressDialog.value=false
                if (user == null) {
                    return
                }
                binding.edAge.setText(user.age)
                binding.filledBloodGroupDropdown.setText(user.bloodGroup)
                binding.edCondition.setText(user.existingHealthConditions)

            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.e("failed--", error.details.toString())
                _showProgressDialog.value=false

            }
        })
    }


}