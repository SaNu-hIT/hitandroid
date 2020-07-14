package com.intellyze.recharge.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.intellyze.recharge.OnItemClickListener
import com.intellyze.recharge.R
import com.intellyze.recharge.cloud.response.transaction.Transaction
import com.intellyze.recharge.databinding.RvTransationItemBinding

class TransactionListAdapter internal constructor(context: Context?) :
    RecyclerView.Adapter<TransactionListAdapter.GroupViewHolder>() {

    private val mInflater: LayoutInflater
    private var mGroups: List<Transaction>? = null
    var onClick: OnItemClickListener? = null

    init {
        mInflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            inflater,
            R.layout.rv_transation_item,
            parent,
            false
        ) as RvTransationItemBinding
        return GroupViewHolder(binding)
    }

    override fun getItemCount(): Int {
        if (null == mGroups)
            return 0;
        else
            return mGroups!!.size;
//        return 8
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

    internal fun setGroups(groupList: List<Transaction>?) {
        mGroups = groupList
        notifyDataSetChanged()
    }

    

    class GroupViewHolder(val binding: RvTransationItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Transaction, onItemClickListener: OnItemClickListener?) {
            binding.data = data
            binding.callback = onItemClickListener
            binding.executePendingBindings()
        }
    }
}