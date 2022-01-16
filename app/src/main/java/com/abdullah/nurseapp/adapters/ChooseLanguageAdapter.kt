package com.abdullah.nurseapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abdullah.nurseapp.R
import com.abdullah.nurseapp.model.ChooseLanguageModel
import com.abdullah.nurseapp.model.HomeModel

class ChooseLanguageAdapter(private val mList: List<ChooseLanguageModel>,var onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<ChooseLanguageAdapter.ViewHolder>() {


    private var currentSelected = 1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_change_language, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        holder.textView.text = ItemsViewModel.language.toString()
        holder.radioButton.isChecked = ItemsViewModel.isSelected
        holder.radioButton.setOnClickListener {
            ItemsViewModel.isSelected = holder.radioButton.isChecked
            mList[currentSelected].isSelected = false
            currentSelected = position
            notifyDataSetChanged()
            onItemClickListener.onItemClick(mList[position])
        }

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.tvLanguage)
        val radioButton: RadioButton = itemView.findViewById(R.id.radioButton)
    }

    interface OnItemClickListener {
        fun onItemClick(item: ChooseLanguageModel?)
    }
}