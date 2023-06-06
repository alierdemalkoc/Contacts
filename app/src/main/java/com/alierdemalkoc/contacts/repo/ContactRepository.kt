package com.alierdemalkoc.contacts.repo

import androidx.lifecycle.MutableLiveData
import com.alierdemalkoc.contacts.dao.ContactDao
import com.alierdemalkoc.contacts.model.Contact
import javax.inject.Inject

class ContactRepository @Inject constructor(
    private val contactDao: ContactDao
)
{
    var contactList: MutableLiveData<List<Contact>> = MutableLiveData()

    suspend fun getAllContact() {
        contactList.postValue(contactDao.getAll())
    }

    suspend fun addContact(contact: Contact) = contactDao.add(contact)

    suspend fun deleteContact(contact: Contact) = contactDao.delete(contact)

    suspend fun updateContact(contact: Contact) = contactDao.update(contact)
}