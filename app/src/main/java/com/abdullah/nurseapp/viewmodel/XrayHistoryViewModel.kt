package com.abdullah.nurseapp.viewmodel

import android.app.Application
import android.content.ContentResolver
import android.net.Uri
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abdullah.nurseapp.model.AddTaskModel
import com.abdullah.nurseapp.utils.USERNAME
import com.abdullah.nurseapp.utils.getStringFromPrefs
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class XrayHistoryViewModel(application: Application) : AndroidViewModel(application) {

    private val _xrayListData = MutableLiveData<List<AddTaskModel>>()
    val xrayListData: LiveData<List<AddTaskModel>>
        get() = _xrayListData
    var databaseReference: DatabaseReference? = null
    var Database_Path = "PatientHistoryDatabase"
    var userName = ""


    init {
        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);
        userName = getStringFromPrefs(getApplication(), USERNAME)!!

    }

    fun getXrayHistoryData() {
        val list = mutableListOf<AddTaskModel>()
        databaseReference!!.child(userName).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {
                    val university: AddTaskModel = postSnapshot.getValue(AddTaskModel::class.java)!!
                    list.add(university)
//                    postSnapshot.ref.removeValue()

                }
                _xrayListData.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.e("failed--", error.details)
            }
        })
    }


}