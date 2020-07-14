package com.intellyze.recharge.ui.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.intellyze.recharge.OnItemClickListener
import com.intellyze.recharge.R

import com.intellyze.recharge.databinding.ViewAllOperatorListBinding


class AllOperatorAdapter internal constructor(context: Context?) :
    RecyclerView.Adapter<AllOperatorAdapter.GroupViewHolder>() {
    private var mGroups: List<com.intellyze.recharge.database.model.DbOperator>? = null
    var onClick: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            inflater,
            R.layout.view_all_operator_list,
            parent,
            false
        ) as ViewAllOperatorListBinding

        return GroupViewHolder(binding)
    }

    override fun getItemCount(): Int {
        if (null == mGroups)
            return 0
        else
            return mGroups!!.size;

    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        if (mGroups != null) {
            val current = mGroups!![position]




            holder.bind(current, onClick)
        } else {
            // Covers the case of data not being ready yet.
            // holder.tv_projectName?.text = "No Projects"
        }
    }

    internal fun setGroups(groupList: List<com.intellyze.recharge.database.model.DbOperator>?) {
        mGroups = groupList
        notifyDataSetChanged()
    }



    class GroupViewHolder(val binding: ViewAllOperatorListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: com.intellyze.recharge.database.model.DbOperator, onItemClickListener: OnItemClickListener?) {
            binding.data = data
            binding.callback = onItemClickListener
            binding.executePendingBindings()
        }
    }
}