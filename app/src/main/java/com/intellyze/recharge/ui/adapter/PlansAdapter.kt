package com.intellyze.recharge.ui.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.intellyze.recharge.OnItemClickListener
import com.intellyze.recharge.R
import com.intellyze.recharge.cloud.response.plans.Plans
import com.intellyze.recharge.database.model.DbPlans
import com.intellyze.recharge.databinding.ViewPlansListBinding
class PlansAdapter internal constructor(context: Context?) :
    RecyclerView.Adapter<PlansAdapter.GroupViewHolder>() {
    private var mGroups: List<DbPlans>? = null
    var onClick: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            inflater,
            R.layout.view_plans_list,
            parent,
            false
        ) as ViewPlansListBinding

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

    internal fun setGroups(groupList: List<DbPlans>?) {
        mGroups = groupList
        notifyDataSetChanged()
    }



    class GroupViewHolder(val binding: ViewPlansListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: DbPlans, onItemClickListener: OnItemClickListener?) {
            binding.data = data
            binding.callback = onItemClickListener
            binding.executePendingBindings()
        }
    }
}