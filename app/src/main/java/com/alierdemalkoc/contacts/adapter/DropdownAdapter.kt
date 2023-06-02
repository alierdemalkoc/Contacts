package com.alierdemalkoc.contacts.adapter

import com.alierdemalkoc.contacts.R
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class DropdownAdapter (private val context: Activity, private var list: List<String>) : ArrayAdapter<String>(context, R.layout.dropdown_item, list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rootView = context.layoutInflater.inflate(R.layout.dropdown_item, null, true)
        val r_group = rootView.findViewById<TextView>(R.id.groupDrop)
        val group = list.get(position)

        r_group.text = group

        return rootView
    }

}