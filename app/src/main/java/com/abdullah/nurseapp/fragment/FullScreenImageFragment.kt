package com.abdullah.nurseapp.fragment

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
import com.abdullah.nurseapp.databinding.FragmentFullScreenImageBinding
import com.abdullah.nurseapp.utils.setToolbarTitleWithBackButton
import com.abdullah.nurseapp.viewmodel.AddTaskViewModel
import com.bumptech.glide.Glide
import java.io.IOException


class FullScreenImageFragment : Fragment() {

    private lateinit var binding: FragmentFullScreenImageBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFullScreenImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar(view)
        val imgUrl = requireArguments().getString("imageUrl")
        Glide.with(this).load(imgUrl).into(binding.zoomImageView);

    }

    private fun setUpToolbar(view: View) {
        val toolbar = view.findViewById<View>(R.id.toolbar) as Toolbar
        setToolbarTitleWithBackButton(requireContext(), toolbar, "Document")
    }

}