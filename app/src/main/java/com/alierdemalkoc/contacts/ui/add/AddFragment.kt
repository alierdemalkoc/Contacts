package com.alierdemalkoc.contacts.ui.add

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alierdemalkoc.contacts.R
import com.alierdemalkoc.contacts.adapter.DropdownAdapter
import com.alierdemalkoc.contacts.databinding.FragmentAddBinding
import com.alierdemalkoc.contacts.model.Contact
import com.alierdemalkoc.contacts.ui.home.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddFragment : Fragment() {
    private val viewModel: AddViewModel by viewModels()

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

        addButton.setOnClickListener {
            it.hideKeyboard()
            if (TextUtils.isEmpty(nameSurnameText.text.toString())){
                binding.nameSurname.error = "Please Insert Name and Surname"
                return@setOnClickListener
            } else if (TextUtils.isEmpty(phoneText.text.toString())){
                binding.phone.error = "Please Insert Phone"
                return@setOnClickListener
            }
            else if (TextUtils.isEmpty(regionText.text.toString())){
                binding.region.error = "Please Insert Region"
                return@setOnClickListener
            }
            else if (TextUtils.isEmpty(autoCompleteTextView.text.toString())){
                binding.group.error = "Please Select Group"
                return@setOnClickListener
            }
            else{
                val contact = Contact(null, nameSurnameText.text.toString(), phoneText.text.toString(), autoCompleteTextView.text.toString(),regionText.text.toString())
                viewModel.addContact(contact)
                Snackbar.make(view, "Başarıyla eklendi", Snackbar.LENGTH_LONG)
                    .setAction("Anasayfaya Dön", View.OnClickListener {
                        findNavController().navigate(R.id.addToHome)
                    }).show()
            }

        }

    }

    fun View.hideKeyboard() {
        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }

}