package com.example.contactmanager.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.contactmanager.R
import com.example.contactmanager.databinding.ActivityAddToContactsBinding
import com.example.contactmanager.model.ContactModel
import com.example.contactmanager.viewmodel.ContactListViewModel

class AddToContactsActivity : AppCompatActivity() {

    private lateinit var addToContactsBinding: ActivityAddToContactsBinding
    private lateinit var contactListViewModel: ContactListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addToContactsBinding= DataBindingUtil.setContentView(this, R.layout.activity_add_to_contacts)
        contactListViewModel= ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))
            .get(ContactListViewModel::class.java)
        addToContactsBinding.apply {

            addToContactButton.setOnClickListener()
            {
                val name=editContactName.text.toString()
                val number=editContactNumber.text.toString()
                val contact=ContactModel(0,name,number)
                contactListViewModel.addContact(contact)
                Toast.makeText(this@AddToContactsActivity,"Contact succesfully added",Toast.LENGTH_SHORT)
                    .show()
                startActivity(Intent(this@AddToContactsActivity, ContactListActivity::class.java))
            }
        }
    }
}