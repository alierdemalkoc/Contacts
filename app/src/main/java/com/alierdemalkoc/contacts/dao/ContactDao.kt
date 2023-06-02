package com.alierdemalkoc.contacts.dao

import androidx.room.*
import com.alierdemalkoc.contacts.model.Contact

@Dao
interface ContactDao {

    @Insert
    fun insert (contact: Contact) : Long

    @Query("select * from contact")
    fun getAll() : List<Contact>

    @Query("select * from contact where nameSurname like :name")
    fun searchName(name: String?) : List<Contact>

    @Query("select * from contact where nid =:nid")
    fun findById(nid: Int) : Contact

    @Query("select * from contact where groups =:groups")
    fun findByGroups(groups: String) : List<Contact>

    @Delete
    fun delete(contact: Contact)

    @Update
    fun update(contact: Contact)


}