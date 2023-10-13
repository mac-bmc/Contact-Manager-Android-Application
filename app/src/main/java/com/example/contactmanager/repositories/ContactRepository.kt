package com.example.contactmanager.repositories

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import com.example.contactmanager.model.ContactDao
import com.example.contactmanager.model.ContactModel


class ContactRepository(private val contactDao: ContactDao) {

    val readAllData: LiveData<List<ContactModel>> = contactDao.readAllData()

    suspend fun addContact(contactModel: ContactModel) {
        contactDao.insertContact(contactModel)
    }

    suspend fun updateContact(contactModel: ContactModel) {

        val name = contactModel.contactName
        val id = contactModel.contactId
        val phNumber = contactModel.contactNumber
        val image = contactModel.contactImage
        contactDao.updatebyId(name, phNumber, id, image)
    }

    suspend fun deleteContact(contactModel: ContactModel) {
        val id = contactModel.contactId
        contactDao.deleteById(id)
    }

    suspend fun deleteAll() {
        contactDao.deleteAll()
    }

    fun makemsg(context: Context, number: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("smsto:$number")
            intent.putExtra("sms_body", "hi")
            context.startActivity(Intent.createChooser(intent, "Header message"));
        } catch (exception: ActivityNotFoundException) {
            Toast.makeText(context, "No messaging app found", Toast.LENGTH_SHORT).show()
        }
    }


}