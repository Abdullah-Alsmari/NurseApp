package com.abdullah.nurseapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import androidx.appcompat.widget.Toolbar
import com.abdullah.nurseapp.R
import com.abdullah.nurseapp.databinding.FragmentFullScreenImageBinding
import com.abdullah.nurseapp.utils.setToolbarTitleWithBackButton
import com.bumptech.glide.Glide


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
        setToolbarTitleWithBackButton(requireContext(), toolbar, resources.getString(R.string.home))
    }

}