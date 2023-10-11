package com.example.contactmanager.repositories
import androidx.lifecycle.LiveData
import com.example.contactmanager.model.ContactDao
import com.example.contactmanager.model.ContactModel

class ContactRepository(private val contactDao: ContactDao) {

    val readAllData: LiveData<List<ContactModel>> = contactDao.readAllData()

    suspend fun addContact(contactModel: ContactModel)
    {
        contactDao.insertContact(contactModel)
    }
    suspend fun updateContact(contactModel: ContactModel)
    {

        val name = contactModel.contactName
        val id=contactModel.contactId
        val phNumber=contactModel.contactNumber
        contactDao.updatebyId(name,phNumber,id)
    }

    suspend fun deleteContact(contactModel: ContactModel)
    {
       val id = contactModel.contactId
        contactDao.deleteById(id)
    }

    suspend fun deleteAll()
    {
        contactDao.deleteAll()
    }


}