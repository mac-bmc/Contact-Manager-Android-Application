package com.example.contactmanager.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.contactmanager.R
import com.example.contactmanager.databinding.ActivityContactDetailBinding
import com.example.contactmanager.model.ContactModel
import com.example.contactmanager.viewmodel.ContactDetailViewModel


class ContactDetailActivity : AppCompatActivity() {

    private lateinit var detailBinding: ActivityContactDetailBinding
    private lateinit var contactDetailViewModel: ContactDetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        detailBinding = DataBindingUtil.setContentView(this, R.layout.activity_contact_detail)
        contactDetailViewModel =
            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))
                .get(ContactDetailViewModel::class.java)

        val name = intent.getStringExtra("name")
        val number = intent.getStringExtra("number")
        detailBinding.apply {
            editContactName.setText(name)
            editContactNumber.setText(number)

            updateContactButton.setOnClickListener {
                val contact = ContactModel(
                    Integer.parseInt(intent.getStringExtra("id")),
                    editContactName.text.toString(),
                    editContactNumber.text.toString()
                )
                contactDetailViewModel.updateContact(contact)
                startActivity(Intent(this@ContactDetailActivity, ContactListActivity::class.java))

            }
            shareButton.setOnClickListener() {

                val name = editContactName.text.toString()
                val number = editContactNumber.text.toString()
                contactDetailViewModel.sharingContact(this@ContactDetailActivity,name,number)

            }
            deleteButton.setOnClickListener{
                val contact = ContactModel(
                    Integer.parseInt(intent.getStringExtra("id")),
                    editContactName.text.toString(),
                    editContactNumber.text.toString()
                )
                Log.d("contact","$contact")

                contactDetailViewModel.deleteContact(contact)
                startActivity(Intent(this@ContactDetailActivity, ContactListActivity::class.java))
            }

            callButton.setOnClickListener{
                val number = editContactNumber.text.toString()
                contactDetailViewModel.call(this@ContactDetailActivity,number)
            }


        }
    }
}