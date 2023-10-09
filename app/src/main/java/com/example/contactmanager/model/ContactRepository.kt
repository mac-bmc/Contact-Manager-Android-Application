package com.example.contactmanager.model
import androidx.lifecycle.LiveData

class ContactRepository(private val contactDao: ContactDao) {

    val readAllData: LiveData<List<ContactModel>> = contactDao.readAllData()

    suspend fun addBook(contactModel: ContactModel)
    {
        contactDao.insertContact(contactModel)
    }
    suspend fun updateBook(contactModel: ContactModel)
    {

        val name = contactModel.contactName
        val id=contactModel.contactId
        val phNumber=contactModel.contactNumber
        contactDao.updatebyId(name,phNumber,id)
    }

    suspend fun deleteBook(contactModel: ContactModel)
    {
       val id = contactModel.contactId
        contactDao.deleteById(id)
    }

    suspend fun deleteAll()
    {
        contactDao.deleteAll()
    }


}