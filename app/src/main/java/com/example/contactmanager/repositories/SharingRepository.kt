package com.example.contactmanager.repositories

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast

class SharingRepository() {


    suspend fun contactSharing(context: Context, name: String, number: String) {
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

    fun call(context: Context, number: String) {
        try {
            val callIntent = Intent()
            callIntent.action = Intent.ACTION_CALL
            callIntent.data = Uri.parse("tell:$number")
            context.startActivity(callIntent)
        } catch (e: Exception) {
            Toast.makeText(context, "${e.toString()}", Toast.LENGTH_SHORT).show()

        }


    }

}