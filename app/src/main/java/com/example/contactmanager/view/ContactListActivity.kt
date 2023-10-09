package com.example.contactmanager.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.contactmanager.R
import com.example.contactmanager.databinding.ActivityContactListBinding

class ContactListActivity : AppCompatActivity() {
    private lateinit var homeBinding: ActivityContactListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding= DataBindingUtil.setContentView(this, R.layout.activity_contact_list)

        homeBinding.recyclerContactListView
    }
}