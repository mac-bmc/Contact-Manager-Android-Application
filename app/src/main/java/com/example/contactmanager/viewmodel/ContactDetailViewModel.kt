package com.example.contactmanager.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactmanager.model.ContactDatabase
import com.example.contactmanager.model.ContactModel
import com.example.contactmanager.model.ContactRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactDetailViewModel(application: Application):AndroidViewModel(application) {

    private  var contactRepository: ContactRepository

    init{
        contactRepository= ContactRepository(ContactDatabase.getDatabase(application).userDao())
    }

    fun updateContact(contactModel: ContactModel)
    {
        viewModelScope.launch(Dispatchers.IO){
            contactRepository.updateContact(contactModel)
        }
    }

    fun deleteContact(contactModel: ContactModel)
    {
        viewModelScope.launch(Dispatchers.IO){
            contactRepository.deleteContact(contactModel)
        }
    }

}