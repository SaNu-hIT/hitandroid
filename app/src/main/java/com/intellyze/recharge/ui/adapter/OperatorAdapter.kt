package com.intellyze.recharge.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.intellyze.recharge.R
import com.intellyze.recharge.model.Operator

class OperatorAdapter(val context: Context?) :
    BaseAdapter() {
    val mInflater: LayoutInflater = LayoutInflater.from(context)
    var list: List<Operator>? = null
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val vh: ItemRowHolder
        if (convertView == null) {
            view = mInflater.inflate(R.layout.view_drop_down_menu, parent, false)
            vh = ItemRowHolder(view)
            view?.tag = vh
        } else {
            view = convertView
            vh = view.tag as ItemRowHolder
        }
        // setting adapter item height programatically.
        val params = view.layoutParams
        params.height = 60
        view.layoutParams = params
        vh.label.text = list?.get(position)?.operatorName
        return view
    }

    override fun getItem(position: Int): Any? {

        return null

    }

    override fun getItemId(position: Int): Long {

        return 0

    }

    internal fun setOperator(loclist: List<Operator>) {
        this.list = loclist
        notifyDataSetChanged()
    }

    override fun getCount(): Int = if (null != list) {
        list?.size!!
    } else {
        0
    }

    private class ItemRowHolder(row: View?) {

        val label: TextView

        init {
            this.label = row?.findViewById(R.id.txtDropDownLabel) as TextView
        }
    }
}

