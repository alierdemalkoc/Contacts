package com.alierdemalkoc.contacts.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact")
data class Contact(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "nid")
    val nid: Int?,

    val nameSurname: String?,
    val number: String?,
    val groups: String?,
    val region: String?

)
