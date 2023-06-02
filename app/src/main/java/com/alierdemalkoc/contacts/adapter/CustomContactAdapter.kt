package com.alierdemalkoc.contacts.adapter

import android.app.Activity
import android.provider.ContactsContract
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.alierdemalkoc.contacts.R
import com.alierdemalkoc.contacts.model.Contact

class CustomContactAdapter (private val context: Activity, private val list: List<Contact>) : ArrayAdapter<Contact>(context, R.layout.custom_list_item, list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rootView = context.layoutInflater.inflate(R.layout.custom_list_item, null, true)

        val r_nameSurname = rootView.findViewById<TextView>(R.id.r_nameSurname)
        val r_region = rootView.findViewById<TextView>(R.id.r_region)
        val r_group = rootView.findViewById<TextView>(R.id.r_group)
        val r_phone = rootView.findViewById<TextView>(R.id.r_phone)


        val contact = list.get(position)

        r_nameSurname.text = "${contact.nameSurname}"
        r_region.text = "${contact.region}"
        r_group.text = "${contact.groups}"
        r_phone.text = "${contact.number}"

        return rootView
    }

}