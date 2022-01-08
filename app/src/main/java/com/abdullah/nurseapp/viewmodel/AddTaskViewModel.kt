package com.abdullah.nurseapp.viewmodel

import android.app.Application
import android.content.ContentResolver
import android.net.Uri
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abdullah.nurseapp.databinding.FragmentAddXrayBinding
import com.abdullah.nurseapp.model.AddTaskModel
import com.abdullah.nurseapp.model.MyDataModel
import com.abdullah.nurseapp.utils.USERNAME
import com.abdullah.nurseapp.utils.getStringFromPrefs
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import android.widget.RadioButton




class AddTaskViewModel(application: Application) : AndroidViewModel(application) {

    private val _showProgressDialog = MutableLiveData<Boolean>(false)
    val showProgressDialog: LiveData<Boolean>
        get() = _showProgressDialog

    var storageReference: StorageReference? = null
    var databaseReference: DatabaseReference? = null
    var Storage_Path = "PatientDocuments/"
    var Database_Path = "PatientHistoryDatabase"
    var userName = ""



    init {
        storageReference = FirebaseStorage.getInstance().reference;
        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);
        userName= getStringFromPrefs(getApplication(), USERNAME)!!

    }

    fun uploadDataToFirebase(fileUri: Uri?,binding: FragmentAddXrayBinding) {
        if (fileUri != null) {
            _showProgressDialog.value = true
            val storageReference2nd = storageReference!!.child(
                Storage_Path + System.currentTimeMillis() + "." + GetFileExtension(fileUri)
            )
            storageReference2nd.putFile(fileUri)
                .addOnSuccessListener { taskSnapshot ->
                    taskSnapshot.storage.downloadUrl.addOnSuccessListener {
                        _showProgressDialog.value = false
                        val imageUrl = it.toString()
                        addTask(imageUrl,binding)
                    }
                }

                .addOnFailureListener(OnFailureListener { e ->
                    Toast.makeText(getApplication(),e.message.toString(),Toast.LENGTH_LONG).show()
                    _showProgressDialog.value = false
                })


        }


    }

    private fun GetFileExtension(uri: Uri?): String? {
        val contentResolver: ContentResolver = getApplication<Application>().contentResolver
        val mimeTypeMap = MimeTypeMap.getSingleton()
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri!!))
    }

    private fun addTask(imgUrl: String,binding: FragmentAddXrayBinding) {
        val selectedId: Int = binding.rgType.getCheckedRadioButtonId()
        val radioButton = binding.rgType.findViewById(selectedId) as RadioButton
        val user = AddTaskModel(
            binding.edTitle.text.toString(),
            binding.edHospName.text.toString(),
            imgUrl,
            radioButton.text.toString()
        )
        databaseReference!!.child(userName).push().setValue(user)
        binding.edHospName.setText("")
        binding.edTitle.setText("")
        Toast.makeText(getApplication(),"Document added Successfully!!!",Toast.LENGTH_LONG).show()

    }

}