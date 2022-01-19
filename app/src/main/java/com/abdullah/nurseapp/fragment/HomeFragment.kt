package com.abdullah.nurseapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.abdullah.nurseapp.R
import com.abdullah.nurseapp.databinding.FragmentHomeBinding
import com.abdullah.nurseapp.adapters.HomeGridAdapter
import com.abdullah.nurseapp.model.HomeModel
import com.smarteist.autoimageslider.SliderView

import com.abdullah.nurseapp.adapters.SliderAdapter

import com.abdullah.nurseapp.model.SliderDataModel
import com.abdullah.nurseapp.utils.setToolbarTitle


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

    private fun setUpToolbar(view: View) {
        val toolbar = view.findViewById<View>(R.id.toolbar) as Toolbar
        setToolbarTitle(requireContext(), toolbar, resources.getString(R.string.home))
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar(view)

    }

    private fun populateData() : List<HomeModel>{
        val list = mutableListOf<HomeModel>()
        list.add(HomeModel(R.string.xray, R.drawable.xrayicon))
        list.add(HomeModel(R.string.blood_test,R.drawable.bloodtestingicon))
        list.add(HomeModel(R.string.Pharmecy,R.drawable.pills))
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
        val url1 = "https://www.redcross.org/content/dam/redcross/about-us/news/2020/coronavirus-safety-tw.jpg"
        val url2 = "http://www.genipulse.com/img/slider/slide3-1.png"
        val url3 =
            "https://sciexaminer.com/wp-content/uploads/2020/07/Healthcare_banner-1.jpg"

        sliderDataArrayList.add(SliderDataModel(url1))
        sliderDataArrayList.add(SliderDataModel(url2))
        sliderDataArrayList.add(SliderDataModel(url3))

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