package com.example.contactmanager.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.contactmanager.R
import com.example.contactmanager.databinding.ActivityContactDetailBinding

class ContactDetailActivity : AppCompatActivity() {

    private lateinit var detailBinding: ActivityContactDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       detailBinding= DataBindingUtil.setContentView(this, R.layout.activity_contact_detail)
    }
}