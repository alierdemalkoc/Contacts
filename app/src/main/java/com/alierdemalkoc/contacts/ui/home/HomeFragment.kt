package com.alierdemalkoc.contacts.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alierdemalkoc.contacts.R
import com.alierdemalkoc.contacts.adapter.CustomContactAdapter
import com.alierdemalkoc.contacts.databinding.FragmentHomeBinding
import com.alierdemalkoc.contacts.model.Contact
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    lateinit var addButton: FloatingActionButton
    lateinit var listView: ListView
    lateinit var searchView: SearchView
    lateinit var adapter: CustomContactAdapter
    var contactAllList = arrayListOf<Contact>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchView = binding.searchView
        listView = binding.contactListView
        adapter = CustomContactAdapter(requireActivity(), contactAllList)
        listView.adapter = adapter
        viewModel.contactList.observe(viewLifecycleOwner){
            contactAllList.addAll(it)
            Log.d("asd", contactAllList.toString())
            adapter.notifyDataSetChanged()
        }



       listView.setOnItemClickListener { adapterView, view, i, l ->
            val bundle = Bundle()
            contactAllList[i].nid?.let { bundle.putInt("id", it) }
            findNavController().navigate(R.id.homeToDetail, bundle)
        }
        addButton = binding.fab
        addButton.setOnClickListener {
            findNavController().navigate(R.id.homeToAdd)
        }
        /*searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                GlobalScope.launch{
                    Log.d("list", "asd")
                    searchList.clear()
                    listDb = AppDatabase.getInstance(requireContext()).contactDao().searchName(p0)
                    for (contact in listDb){
                        searchList.add(contact)
                        val adapter = CustomContactAdapter(requireActivity(), searchList)
                        listView.adapter = adapter
                    }
                    Log.d("list", listDb.toString())
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}