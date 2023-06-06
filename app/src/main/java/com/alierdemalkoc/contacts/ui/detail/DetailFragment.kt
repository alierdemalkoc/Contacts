package com.alierdemalkoc.contacts.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.alierdemalkoc.contacts.R
import com.alierdemalkoc.contacts.adapter.DropdownAdapter
import com.alierdemalkoc.contacts.config.AppDatabase
import com.alierdemalkoc.contacts.databinding.FragmentDetailBinding
import com.alierdemalkoc.contacts.model.Contact
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    lateinit var nameSurnameText: EditText
    lateinit var phoneText: EditText
    lateinit var regionText: EditText
    lateinit var groupText: TextView
    lateinit var updateButton: Button
    lateinit var editButton: Button
    lateinit var deleteButton: Button
    lateinit var autoCompleteTextView: AutoCompleteTextView
    lateinit var textInputLayout: TextInputLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nameSurnameText = binding.nameSurnameText
        phoneText = binding.phoneText
        regionText = binding.regionText
        groupText = binding.groupText
        textInputLayout = binding.textInputEdit
        textInputLayout.visibility = View.GONE
        deleteButton = binding.deleteButton
        deleteButton.visibility = View.GONE
        autoCompleteTextView = binding.group
        updateButton = binding.updateButton
        updateButton.visibility = View.GONE
        editButton = binding.editButton

        val groups = listOf<String>("Aile", "İş", "Dernek", "Arkadaşlar")
        val dropdownAdapter =
            DropdownAdapter(requireActivity(), groups)
        autoCompleteTextView.setAdapter(dropdownAdapter)

        val id = arguments?.getInt("id")
     /*   CoroutineScope(Dispatchers.IO).launch {

            val contact = id?.let { AppDatabase.getInstance(requireContext()).contactDao().findById(it) }
            Log.d("contact", contact.toString())
            nameSurnameText.setText(contact!!.nameSurname.toString())
            phoneText.setText(contact.number)
            regionText.setText(contact.region)
            groupText.text = (contact.groups)
        }*/

        editButton.setOnClickListener {
            editButton.visibility = View.GONE
            groupText.visibility = View.GONE
            textInputLayout.visibility = View.VISIBLE
            deleteButton.visibility = View.VISIBLE
            updateButton.visibility = View.VISIBLE

        }

     /*   updateButton.setOnClickListener {
            if (nameSurnameText.text.toString() != "" && phoneText.text.toString() != "" && autoCompleteTextView.text.toString() != "" && regionText.text.toString() != "") {
                CoroutineScope(Dispatchers.IO).launch {
                    val contact = Contact(
                        id,
                        nameSurnameText.text.toString(),
                        phoneText.text.toString(),
                        autoCompleteTextView.text.toString(),
                        regionText.text.toString()
                    )
                    AppDatabase.getInstance(requireContext()).contactDao().update(contact)
                }
            } else {
                Toast.makeText(requireContext(), "Please Insert", Toast.LENGTH_LONG).show()
            }
            Toast.makeText(requireContext(), "Başarıyla Güncellenmiştir", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.detailToHome)
        }
        deleteButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val contact = Contact(
                    id,
                    nameSurnameText.text.toString(),
                    phoneText.text.toString(),
                    autoCompleteTextView.text.toString(),
                    regionText.text.toString()
                )
                AppDatabase.getInstance(requireContext()).contactDao().delete(contact)
            }
            Toast.makeText(requireContext(), "Başarıyla Silinmiştir", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.detailToHome)
        }*/

    }
}