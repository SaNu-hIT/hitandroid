package com.intellyze.recharge.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.intellyze.OnItemSelctedListener
import com.intellyze.recharge.OnItemClickListener
import com.intellyze.recharge.R
import com.intellyze.recharge.database.model.DbOperator
import com.intellyze.recharge.databinding.FragmentAllOperatorDialogBinding
import com.intellyze.recharge.ui.adapter.AllOperatorAdapter
import kotlinx.android.synthetic.main.fragment_all_operator_dialog.*

/**
 * A simple [Fragment] subclass.
 */
class OperatorDialogFragment (val itemClickListener: OnItemSelctedListener) : BottomSheetDialogFragment(), OnItemClickListener {
    var binding: FragmentAllOperatorDialogBinding? = null
    var adapters: AllOperatorAdapter? = null
            lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_all_operator_dialog, container, false)
        binding?.fragment = this
        return binding?.root
    }

    private fun initView(view: View?) {
        recyclerView = binding?.plassrecycler!!
        recyclerView.adapter = adapters
        adapters?.onClick = this
        recyclerView.layoutManager = LinearLayoutManager(context)

        tvSelectionText.text = titles

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

    fun setData(dbOperators: List<DbOperator>?) {

      var  operators = dbOperators
        adapters = AllOperatorAdapter(context)
        adapters?.setGroups(operators)
    }

    override fun onItemClick(v: View, obj: Any) {
        if(obj is DbOperator) {
            itemClickListener.onItemSelected(obj)
            dismiss()
        }

    }

    var titles = ""

    fun setTitle(type: String) {
        if (type=="MOBILE")
        {
            titles ="Operators"
        }else if (type=="DTH"){
            titles="Providers"
        }

    }
}
