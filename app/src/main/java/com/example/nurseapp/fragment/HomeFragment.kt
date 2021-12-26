package com.example.nurseapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.abdullah.nurseapp.databinding.FragmentHomeBinding
import com.example.nurseapp.adapters.HomeGridAdapter
import com.example.nurseapp.model.HomeModel
import com.smarteist.autoimageslider.SliderView

import com.example.nurseapp.adapters.SliderAdapter

import android.R
import com.example.nurseapp.model.SliderDataModel


class HomeFragment : Fragment(){

    private lateinit var binding : FragmentHomeBinding
    private lateinit var adapter: HomeGridAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val dataList = populateData()
        setupRecyclerView(dataList)
        setupSliderData()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Home"

    }

    private fun populateData() : List<HomeModel>{
        val list = mutableListOf<HomeModel>()
        list.add(HomeModel("X-ray"))
        list.add(HomeModel("Blood Testing"))
        return list

    }
    private fun setupRecyclerView(dataList : List<HomeModel>){
        adapter = HomeGridAdapter()
        binding.recyclerView.setHasFixedSize(true)
        adapter.submitList(dataList)
        binding.recyclerView.adapter = adapter

    }

    private fun setupSliderData(){  // we are creating array list for storing our image urls.
        // we are creating array list for storing our image urls.
        val sliderDataArrayList: ArrayList<SliderDataModel> = ArrayList()

        // initializing the slider view.

        // initializing the slider view.
        val sliderView: SliderView = binding.imageSlider

        // adding the urls inside array list

        // adding the urls inside array list
        // Urls of our images.
        // Urls of our images.
        val url1 = "https://www.geeksforgeeks.org/wp-content/uploads/gfg_200X200-1.png"
        val url2 = "https://qphs.fs.quoracdn.net/main-qimg-8e203d34a6a56345f86f1a92570557ba.webp"
        val url3 =
            "https://bizzbucket.co/wp-content/uploads/2020/08/Life-in-The-Metro-Blog-Title-22.png"

        sliderDataArrayList.add(SliderDataModel(url1))
        sliderDataArrayList.add(SliderDataModel(url1))
        sliderDataArrayList.add(SliderDataModel(url1))

        // passing this array list inside our adapter class.

        // passing this array list inside our adapter class.
        val adapter = SliderAdapter(requireActivity(), sliderDataArrayList)

        // below method is used to set auto cycle direction in left to
        // right direction you can change according to requirement.

        // below method is used to set auto cycle direction in left to
        // right direction you can change according to requirement.
        sliderView.autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR

        // below method is used to
        // setadapter to sliderview.

        // below method is used to
        // setadapter to sliderview.
        sliderView.setSliderAdapter(adapter)

        // below method is use to set
        // scroll time in seconds.

        // below method is use to set
        // scroll time in seconds.
        sliderView.scrollTimeInSec = 3

        // to set it scrollable automatically
        // we use below method.

        // to set it scrollable automatically
        // we use below method.
        sliderView.isAutoCycle = true

        // to start autocycle below method is used.

        // to start autocycle below method is used.
        sliderView.startAutoCycle()

    }
}