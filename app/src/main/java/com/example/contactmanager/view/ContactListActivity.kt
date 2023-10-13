package com.example.contactmanager.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactmanager.R
import com.example.contactmanager.databinding.ActivityContactListBinding
import com.example.contactmanager.viewmodel.ContactListViewModel

class ContactListActivity : AppCompatActivity() {
    private lateinit var homeBinding: ActivityContactListBinding
    private lateinit var contactListViewModel:ContactListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding= DataBindingUtil.setContentView(this, R.layout.activity_contact_list)

        val recView=homeBinding.recyclerContactListView
        val adapter = ContactListViewAdapter(this@ContactListActivity)
        recView.adapter=adapter
        recView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        contactListViewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory(application))
            .get(ContactListViewModel::class.java)

        contactListViewModel.contactData.observe(this, Observer { contact->
            if (contact.isEmpty())
            {
                homeBinding.noData.isVisible=true
            }
            adapter.setContacts(contact)

        })

        homeBinding.apply {

            floatingActionButton.setOnClickListener{
                val intent=Intent(this@ContactListActivity, AddToContactsActivity::class.java)
                startActivity(intent)
            }

            deleteAllButton.setOnClickListener{
                val builder= AlertDialog.Builder(this@ContactListActivity)
                builder.setTitle("Deletion Alert")
                builder.setMessage("Are you sure to delete the data")
                builder.setPositiveButton(R.string.yes){ dialog, which ->
                    contactListViewModel.deleteAll()
                    homeBinding.noData.isVisible=true
                }
                builder.setNegativeButton(R.string.no){ dialog, which ->
                    Toast.makeText(this@ContactListActivity,"Delete Action Suspended",Toast.LENGTH_SHORT).show()

                }
                builder.show()


            }
        }


    }
}