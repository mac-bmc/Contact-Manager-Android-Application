@file:Suppress("DEPRECATION")

package com.example.contactmanager.view

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.contactmanager.R
import com.example.contactmanager.databinding.ActivityAddToContactsBinding
import com.example.contactmanager.model.ContactModel
import com.example.contactmanager.viewmodel.ContactListViewModel
import java.io.File

class AddToContactsActivity : AppCompatActivity() {

    private lateinit var addToContactsBinding: ActivityAddToContactsBinding
    private lateinit var contactListViewModel: ContactListViewModel
     lateinit var imageUri:Uri
    val takePicture = registerForActivityResult(ActivityResultContracts.TakePicture()){success->
        Toast.makeText(this@AddToContactsActivity,"saved",Toast.LENGTH_SHORT).show()

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_DENIED ||
                checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_DENIED) {
                val permission = arrayOf(
                    android.Manifest.permission.CAMERA,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                requestPermissions(permission, 112)
            }
        }


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
            imageAdd.setOnClickListener{
                val builder= AlertDialog.Builder(this@AddToContactsActivity)
                builder.setTitle("Add Photo")
                builder.setMessage("How to upload data")
                builder.setPositiveButton(R.string.cam){ dialog, which ->
                    val file = File(filesDir,"${editContactName.text}"+".png")
                    /*if (Build.VERSION.SDK_INT < 24) {
                        imageUri = Uri.fromFile(file)
                    } else {
                        imageUri = Uri.parse(file.getPath())}
                    takePicture.launch(imageUri)
                    Log.d("uri","$imageUri")
                    contactImage.setImageURI(Uri.parse("file://"+"$imageUri"))
                    Glide.with(this@AddToContactsActivity)
                        .load(Uri.parse("file://"+"$imageUri"))
                        .into(contactImage)*/
                    val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(cameraIntent, 121)
                }
                /*builder.setPositiveButton(R.string.gallery){dialog, which ->

                }*/
                builder.setNegativeButton(R.string.cancel){ dialog, which ->
                    Toast.makeText(this@AddToContactsActivity,"Cancelled",Toast.LENGTH_SHORT).show()


                }
                builder.show()

            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 121 && data != null){
            addToContactsBinding.contactImage.setImageBitmap(data.extras?.get("data") as Bitmap)
        }
    }
}