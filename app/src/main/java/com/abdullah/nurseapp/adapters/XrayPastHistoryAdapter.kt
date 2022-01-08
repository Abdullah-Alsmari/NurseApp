package com.abdullah.nurseapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.abdullah.nurseapp.R
import com.abdullah.nurseapp.databinding.ItemXrayHistoryBinding
import com.abdullah.nurseapp.model.AddTaskModel
import com.abdullah.nurseapp.model.ChooseLanguageModel

class XrayPastHistoryAdapter(
    context: Context,
    addTaskList: List<AddTaskModel>?,
    var onItemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<XrayPastHistoryAdapter.XrayHistoryViewHolder>() {

    private var mContext: Context = context
    private var addTaskList: List<AddTaskModel>? = addTaskList


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): XrayHistoryViewHolder {
        val binding: ItemXrayHistoryBinding = DataBindingUtil.inflate(
            LayoutInflater.from(mContext),
            R.layout.item_xray_history,
            parent,
            false
        )
        return XrayHistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: XrayHistoryViewHolder, position: Int) {
        val missingItem = addTaskList?.get(position)
        holder.bind(missingItem, onItemClickListener)

    }

    override fun getItemCount(): Int {
        return addTaskList!!.size

    }

    class XrayHistoryViewHolder(itemView: ItemXrayHistoryBinding) :
        RecyclerView.ViewHolder(itemView.root) {

        private val itemViewBinding: ItemXrayHistoryBinding = itemView

        fun bind(addtaskModel: AddTaskModel?, onItemClickListener: OnItemClickListener) {
            itemViewBinding.modelData = addtaskModel
            itemViewBinding.cardView.setOnClickListener {
                onItemClickListener.onItemClick(addtaskModel)
            }
            itemViewBinding.imgDelete.setOnClickListener {
                onItemClickListener.onDeleteClick(addtaskModel)


            }
            itemViewBinding.imgShare.setOnClickListener {
                onItemClickListener.onShareClick(addtaskModel)

            }
            itemViewBinding.executePendingBindings()
        }

    }

    interface OnItemClickListener {
        fun onItemClick(item: AddTaskModel?)
        fun onDeleteClick(item: AddTaskModel?)
        fun onShareClick(item: AddTaskModel?)

    }

}