package com.example.contactmanager.repositories

import android.content.Context
import android.content.Intent
import android.net.Uri

class SharingRepository() {


    suspend fun contactSharing(context:Context,name:String,number:String)
    {
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type = "text/plain"
        shareIntent.putExtra(
            Intent.EXTRA_TEXT,
            "Contact Name : $name"
        )
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Contact Number : $number")
        context.startActivity(Intent.createChooser(shareIntent, "Share Contact"))

    }

    suspend fun call(context: Context,number:String)
    {
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "$number"))
        context.startActivity(intent)


    }

}