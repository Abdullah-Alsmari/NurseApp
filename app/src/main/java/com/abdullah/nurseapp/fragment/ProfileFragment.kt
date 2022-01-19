package com.abdullah.nurseapp.fragment

import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abdullah.nurseapp.LoginActivity
import com.abdullah.nurseapp.R
import com.abdullah.nurseapp.adapters.ChooseLanguageAdapter
import com.abdullah.nurseapp.databinding.FragmentProfileBinding
import com.abdullah.nurseapp.model.ChooseLanguageModel
import com.abdullah.nurseapp.utils.*
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth
import java.util.*

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //  night Mode Switch
//        binding.switchtheme.setOnCheckedChangeListener { _, isChecked ->
//            if (isChecked) {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//            } else {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//            }
//
//        }

    }

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
        binding.tvLogout.setOnClickListener {
            logOut()
        }
        binding.changeLanguage.setOnClickListener {
            showBottomSheetDialog()
        }
        binding.tvProfile.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToMyDataFragment())
        }
        (activity as AppCompatActivity).supportActionBar?.title = R.string.profile.toString()
        return binding.root



    }

    private fun logOut() {
        AlertDialog.Builder(requireActivity())
        val builder = android.app.AlertDialog.Builder(context)
        builder.setTitle(R.string.Are_you_sure)
        builder.setPositiveButton(R.string.yes) { _, _ ->
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(requireActivity(), LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
        builder.setNegativeButton(R.string.No) { dialog, _ ->
            dialog.cancel()
        }
        builder.create().show()
    }

    private fun showBottomSheetDialog() {
        val bottomSheetDialog = BottomSheetDialog(requireActivity())
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog)
        val recyclerView = bottomSheetDialog.findViewById<RecyclerView>(R.id.rvChooseLanguage)
        recyclerView!!.layoutManager = LinearLayoutManager(requireActivity())
        val dataList = mutableListOf<ChooseLanguageModel>()
        if (getStringFromPrefs(requireActivity(), LANGUAGECODE) == "en") {
            dataList.add(ChooseLanguageModel("English", true))
            dataList.add(ChooseLanguageModel("العربية", false))

        } else {
            dataList.add(ChooseLanguageModel("English" ,false))
            dataList.add(ChooseLanguageModel("العربية",true))

        }
        val adapter =
            ChooseLanguageAdapter(dataList, object : ChooseLanguageAdapter.OnItemClickListener {
                override fun onItemClick(item: ChooseLanguageModel?) {
                    bottomSheetDialog.dismiss()
                    val languageCode = if (item!!.language == "English") {
                        "en"
                    } else {
                        "ar"
                    }
                    setPrefsString(requireActivity(), LANGUAGECODE, languageCode)
                    changeLocale(languageCode)

                }


            })
        recyclerView!!.adapter = adapter
        bottomSheetDialog.show()
    }

    private fun changeLocale(language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val resources: Resources = requireActivity().resources
        val config: Configuration = resources.getConfiguration()
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.getDisplayMetrics())
        requireActivity().recreate()
    }


}