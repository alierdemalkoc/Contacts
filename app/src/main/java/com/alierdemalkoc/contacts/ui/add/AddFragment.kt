package com.alierdemalkoc.contacts.ui.add

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.alierdemalkoc.contacts.R
import com.alierdemalkoc.contacts.adapter.DropdownAdapter
import com.alierdemalkoc.contacts.config.AppDatabase
import com.alierdemalkoc.contacts.databinding.FragmentAddBinding
import com.alierdemalkoc.contacts.model.Contact
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddFragment : Fragment() {
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    lateinit var nameSurnameText: EditText
    lateinit var phoneText: EditText
    lateinit var regionText: EditText
    lateinit var addButton: Button
    lateinit var autoCompleteTextView: AutoCompleteTextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nameSurnameText = binding.nameSurname
        phoneText = binding.phone
        regionText = binding.region
        addButton = binding.addButton
        autoCompleteTextView = binding.group
        val dropdownAdapter = DropdownAdapter(requireActivity(), listOf("Aile", "İş", "Dernek", "Arkadaşlar"))
        autoCompleteTextView.setAdapter(dropdownAdapter)

      /*  addButton.setOnClickListener {
            it.hideKeyboard()
            if (nameSurnameText.text.toString() != "" && phoneText.text.toString() != "" && autoCompleteTextView.text.toString() != "" && regionText.text.toString() != ""){
                CoroutineScope(Dispatchers.IO).launch {
                    val contact = Contact(null, nameSurnameText.text.toString(), phoneText.text.toString(), autoCompleteTextView.text.toString(),regionText.text.toString())
                    AppDatabase.getInstance(requireContext()).contactDao().insert(contact)
                    val list = AppDatabase.getInstance(requireContext()).contactDao().getAll()
                    Log.d("list", list.toString())
                    Snackbar.make(view, "Başarıyla eklendi", Snackbar.LENGTH_LONG)
                        .setAction("Anasayfaya Dön", View.OnClickListener {
                            findNavController().navigate(R.id.addToHome)
                        }).show()
                }

            } else {
                Toast.makeText(requireContext(), "Please Insert", Toast.LENGTH_LONG).show()
            }

        }*/



    }

    fun View.hideKeyboard() {
        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }

}