package com.example.nurseapp.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.abdullah.nurseapp.LoginActivity
import com.abdullah.nurseapp.databinding.FragmentProfileBinding
import com.example.nurseapp.utils.EMAIL
import com.example.nurseapp.utils.PHONENUMBER
import com.example.nurseapp.utils.USERNAME
import com.example.nurseapp.utils.getStringFromPrefs
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        val email = getStringFromPrefs(requireActivity(), EMAIL)
        val name = getStringFromPrefs(requireActivity(), USERNAME)
        val phoneNumber = getStringFromPrefs(requireActivity(), PHONENUMBER)
        binding.userEmail.setText(email)
        binding.userName.setText(name)
        binding.userMobile.setText(phoneNumber)
        binding.tvLogout.setOnClickListener {
            logOut()
        }
        return binding.root
    }

    private fun logOut() {
        AlertDialog.Builder(requireActivity())
        val builder = android.app.AlertDialog.Builder(context)
        builder.setTitle("Are you sure you want to sign out?")
        builder.setPositiveButton("Yes") { _, _ ->
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(requireActivity(),LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
        builder.setNegativeButton("No") { dialog, _ ->
            dialog.cancel()
        }
        builder.create().show()
    }
}