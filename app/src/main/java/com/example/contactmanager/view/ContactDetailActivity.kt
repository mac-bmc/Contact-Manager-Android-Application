package com.example.contactmanager.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.InputType
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.contactmanager.R
import com.example.contactmanager.databinding.ActivityContactDetailBinding
import com.example.contactmanager.model.ContactModel
import com.example.contactmanager.viewmodel.ContactDetailViewModel


class ContactDetailActivity : AppCompatActivity() {

    private lateinit var detailBinding: ActivityContactDetailBinding
    private lateinit var contactDetailViewModel: ContactDetailViewModel
    lateinit var imageUrl: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        detailBinding = DataBindingUtil.setContentView(this, R.layout.activity_contact_detail)
        contactDetailViewModel =
            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))
                .get(ContactDetailViewModel::class.java)

        val name = intent.getStringExtra("name")
        val number = intent.getStringExtra("number")
        imageUrl = intent.getStringExtra("image").toString()
        detailBinding.apply {
            editContactName.setText(name)
            editContactNumber.setText(number)

            if (imageUrl != "") {
                Glide.with(this@ContactDetailActivity)
                    .load(Uri.parse(imageUrl))
                    .error(R.drawable.placeholder_image)
                    .into(contactImage)

            }

            updateContactButton.setOnClickListener {
                val contact = ContactModel(
                    intent.getIntExtra("id", 0),
                    editContactName.text.toString(),
                    editContactNumber.text.toString(),
                    imageUrl
                )
                contactDetailViewModel.updateContact(contact)
                startActivity(Intent(this@ContactDetailActivity, ContactListActivity::class.java))

            }
            shareButton.setOnClickListener() {

                val name = editContactName.text.toString()
                val num = editContactNumber.text.toString()
                contactDetailViewModel.sharingContact(this@ContactDetailActivity, name, num)

            }
            deleteButton.setOnClickListener {
                val contact = ContactModel(
                    intent.getIntExtra("id", 0),
                    editContactName.text.toString(),
                    editContactNumber.text.toString(),
                    imageUrl
                )
                Log.d("contact", "$contact")

                contactDetailViewModel.deleteContact(contact)
                startActivity(Intent(this@ContactDetailActivity, ContactListActivity::class.java))
            }

            callButton.setOnClickListener {
                val number = editContactNumber.text.toString()
                contactDetailViewModel.call(this@ContactDetailActivity, number)
            }
            msgButton.setOnClickListener {
                contactDetailViewModel.msg(
                    this@ContactDetailActivity,
                    editContactNumber.text.toString()
                )


            }
            editContactButton.setOnClickListener {
                editContactName.inputType = InputType.TYPE_CLASS_TEXT
                editContactName.isEnabled = true
                editContactNumber.inputType = InputType.TYPE_CLASS_NUMBER
                editContactNumber.isEnabled = true
            }


        }
    }
}