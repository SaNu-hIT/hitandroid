package com.intellyze.recharge.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.intellyze.OnItemSelctedListener
import com.intellyze.recharge.OnItemClickListener
import com.intellyze.recharge.R
import com.intellyze.recharge.cloud.response.plans.PlansResponse
import com.intellyze.recharge.database.model.DbPlans
import com.intellyze.recharge.databinding.FragmentPlansDialogBinding
import com.intellyze.recharge.ui.adapter.PlansAdapter

/**
 * A simple [Fragment] subclass.
 */
class PlansDialogFragment(val itemClickListener: OnItemSelctedListener)  : BottomSheetDialogFragment(),
    OnItemClickListener {
    var binding: FragmentPlansDialogBinding? = null
    var adapters:PlansAdapter? = null

            lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_plans_dialog, container, false)
        binding?.fragment = this
        return binding?.root
    }

    private fun initView(view: View?) {
        recyclerView = binding?.plassrecycler!!
        recyclerView.adapter = adapters
        adapters?.onClick = this
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

     fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_done -> {
                dismiss()
            }
            R.id.iv_back -> {
                dismiss()
            }
        }
    }

    fun setData(t: List<DbPlans>?) {

      var  transactions = t
        adapters = PlansAdapter(context)
        adapters?.setGroups(transactions)
    }

    override fun onItemClick(v: View, obj: Any) {

        if(obj is DbPlans)
        {
            itemClickListener.onItemSelected(obj)
            dismiss()
        }

    }
}
