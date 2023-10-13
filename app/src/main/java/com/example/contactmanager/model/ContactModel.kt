package com.example.contactmanager.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ContactsDetails")
data class ContactModel(
    @PrimaryKey(autoGenerate = true)
    val contactId: Int,
    val contactName: String,
    val contactNumber: String,
    val contactImage: String
)