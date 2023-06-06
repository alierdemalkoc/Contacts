package com.alierdemalkoc.contacts.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alierdemalkoc.contacts.model.Contact
import com.alierdemalkoc.contacts.repo.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val contactRepository: ContactRepository) : ViewModel(){

    val carList: LiveData<List<Contact>> = contactRepository.contactList

    init {
        getAllCar()
    }
    private fun getAllCar(){
        viewModelScope.launch {
            contactRepository.getAllContact()
        }
    }

}