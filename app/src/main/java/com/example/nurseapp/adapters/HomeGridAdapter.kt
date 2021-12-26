package com.example.nurseapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abdullah.nurseapp.databinding.ItemHomeBinding
import com.example.nurseapp.fragment.HomeFragmentDirections

import com.example.nurseapp.model.HomeModel

class HomeGridAdapter() :
    ListAdapter<HomeModel, HomeGridAdapter.MovieViewHolder>(DiffCallback) {
    lateinit var action: NavDirections

    inner class MovieViewHolder(private var binding: ItemHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HomeModel) {
            binding.home = item
            binding.executePendingBindings()
            binding.cardView.setOnClickListener {
                if (item.name.equals("X-ray")) {
                    action = HomeFragmentDirections.actionHomeFragmentToXrayPastHistoryFragment()
                } else {
                    action = HomeFragmentDirections.actionHomeFragmentToBloodTestPastHistoryFragment()
                }
                binding.root.findNavController()
                    .navigate(action)
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(ItemHomeBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<HomeModel>() {
        override fun areItemsTheSame(
            oldItem: HomeModel,
            newItem: HomeModel
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: HomeModel,
            newItem: HomeModel
        ): Boolean {
            return oldItem.name == newItem.name
        }
    }
}
