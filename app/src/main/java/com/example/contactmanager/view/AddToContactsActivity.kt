package com.example.contactmanager.view

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.contactmanager.R
import com.example.contactmanager.databinding.ActivityAddToContactsBinding
import com.example.contactmanager.model.ContactModel
import com.example.contactmanager.viewmodel.ContactListViewModel


class AddToContactsActivity : AppCompatActivity() {

    private lateinit var addToContactsBinding: ActivityAddToContactsBinding
    private lateinit var contactListViewModel: ContactListViewModel
    private lateinit var galleryLauncher: ActivityResultLauncher<Intent>
    private var imageUri: Uri? = null
    private var imageUrl: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (checkSelfPermission(android.Manifest.permission.CAMERA) ==
            PackageManager.PERMISSION_DENIED ||
            checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            == PackageManager.PERMISSION_DENIED
        ) {
            val permission = arrayOf(
                android.Manifest.permission.CAMERA,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            requestPermissions(permission, 112)
        }


        addToContactsBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_add_to_contacts)
        contactListViewModel =
            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))
                .get(ContactListViewModel::class.java)
        galleryLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                imageUri = result.data?.data
                imageUrl = imageUri.toString()
                Glide.with(this@AddToContactsActivity)
                    .load(imageUri)
                    .into(addToContactsBinding.contactImage)
            }
        addToContactsBinding.apply {

            addToContactButton.setOnClickListener()
            {
                val name = editContactName.text.toString()
                val number = editContactNumber.text.toString()
                if (imageUrl == null) {
                    imageUrl = ""
                }

                val contact = ContactModel(0, name, number, imageUrl.toString())
                val isValidated = contactListViewModel.isValidated(contact)
                if (isValidated) {
                    contactListViewModel.addContact(contact)
                    Toast.makeText(
                        this@AddToContactsActivity,
                        "Contact successfully added",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    startActivity(
                        Intent(
                            this@AddToContactsActivity,
                            ContactListActivity::class.java
                        )
                    )
                } else {
                    Toast.makeText(
                        this@AddToContactsActivity,
                        "Invalid credentials",
                        Toast.LENGTH_SHORT
                    )
                        .show()

                }


            }
            imageAdd.setOnClickListener {
                val builder = AlertDialog.Builder(this@AddToContactsActivity)
                builder.setTitle("Add Photo")
                builder.setMessage("Pick Image from Gallery")
                builder.setPositiveButton(R.string.gallery) { _, _ ->
                    val intent =
                        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    galleryLauncher.launch(intent)

                }

                builder.setNegativeButton(R.string.cancel) { _, _ ->
                    Toast.makeText(this@AddToContactsActivity, "Cancelled", Toast.LENGTH_SHORT)
                        .show()


                }
                builder.show()

            }
        }
    }


}