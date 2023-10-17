package com.example.contactmanager.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.contactmanager.model.ContactDatabase
import com.example.contactmanager.model.ContactModel
import com.example.contactmanager.repositories.ContactRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactListViewModel(application: Application) : AndroidViewModel(application) {

    private  var contactRepository: ContactRepository
     var contactData: LiveData<List<ContactModel>>

    init {
        val contactDao = ContactDatabase.getDatabase(application).userDao()
        contactRepository = ContactRepository(contactDao)
        contactData = contactRepository.readAllData
    }

    fun addContact(contactModel: ContactModel) {
        viewModelScope.launch(Dispatchers.IO)
        {
            contactRepository.addContact(contactModel)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            contactRepository.deleteAll()
        }
    }
    fun isValidated(contactModel: ContactModel):Boolean
    {
        return contactRepository.isValidated(contactModel)
    }


}