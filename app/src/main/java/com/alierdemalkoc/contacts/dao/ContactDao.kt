package com.alierdemalkoc.contacts.dao

import androidx.room.*
import com.alierdemalkoc.contacts.model.Contact

@Dao
interface ContactDao {

    @Insert
    suspend fun add(contact: Contact) : Long

    @Query("select * from contact")
    suspend fun getAll() : List<Contact>

    @Query("select * from contact where nameSurname like :name")
    suspend fun searchName(name: String?) : List<Contact>

    @Query("select * from contact where nid =:nid")
    suspend fun findById(nid: Int) : Contact

    @Query("select * from contact where groups =:groups")
    suspend fun findByGroups(groups: String) : List<Contact>

    @Delete
    suspend fun delete(contact: Contact)

    @Update
    suspend fun update(contact: Contact)


}