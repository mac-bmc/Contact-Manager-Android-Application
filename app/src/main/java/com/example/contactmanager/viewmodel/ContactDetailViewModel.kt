package com.example.contactmanager.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactmanager.model.ContactDatabase
import com.example.contactmanager.model.ContactModel
import com.example.contactmanager.repositories.ContactRepository
import com.example.contactmanager.repositories.SharingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactDetailViewModel(application: Application) : AndroidViewModel(application) {

    private var contactRepository: ContactRepository
    private var sharingRepository: SharingRepository

    init {
        contactRepository = ContactRepository(ContactDatabase.getDatabase(application).userDao())
        sharingRepository = SharingRepository()
    }

    fun updateContact(contactModel: ContactModel) {
        viewModelScope.launch(Dispatchers.IO) {
            contactRepository.updateContact(contactModel)
        }
    }

    fun deleteContact(contactModel: ContactModel) {
        viewModelScope.launch(Dispatchers.IO) {
            contactRepository.deleteContact(contactModel)
        }
    }

    fun sharingContact(context: Context, name: String, number: String) {
        viewModelScope.launch(Dispatchers.IO) {
            sharingRepository.contactSharing(context, name, number)
        }
    }

    fun call(context: Context, number: String) {
        sharingRepository.call(context, number)
    }

    fun msg(context: Context, number: String) {
        contactRepository.makemsg(context, number)
    }


}