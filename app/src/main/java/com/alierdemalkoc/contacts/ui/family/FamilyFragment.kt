package com.alierdemalkoc.contacts.ui.family

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.navigation.fragment.findNavController
import com.alierdemalkoc.contacts.R
import com.alierdemalkoc.contacts.adapter.CustomContactAdapter
import com.alierdemalkoc.contacts.config.AppDatabase
import com.alierdemalkoc.contacts.databinding.FragmentDetailBinding
import com.alierdemalkoc.contacts.databinding.FragmentFamilyBinding
import com.alierdemalkoc.contacts.model.Contact
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FamilyFragment : Fragment() {
    private var _binding: FragmentFamilyBinding? = null
    private val binding get() = _binding!!

    lateinit var listView: ListView
    var contactList = mutableListOf<Contact>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFamilyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listView = binding.familyListView

        CoroutineScope(Dispatchers.IO).launch{
            contactList.clear()
            val listDb = AppDatabase.getInstance(requireContext()).contactDao().findByGroups("Aile")
            for (contact in listDb){
                contactList.add(contact)
                val adapter = CustomContactAdapter(requireActivity(), contactList)
                listView.adapter = adapter
            }
        }

        listView.setOnItemClickListener { adapterView, view, i, l ->
            val bundle = Bundle()
            contactList[i].nid?.let { bundle.putInt("id", it) }
            findNavController().navigate(R.id.familyToDetail, bundle)
        }

    }

}