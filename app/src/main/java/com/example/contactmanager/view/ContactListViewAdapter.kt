package com.example.contactmanager.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import com.example.contactmanager.R
import androidx.recyclerview.widget.RecyclerView
import com.example.contactmanager.databinding.ActivityContactListBinding
import com.example.contactmanager.databinding.RecyclerContactListViewBinding
import com.example.contactmanager.model.ContactModel

class ContactListViewAdapter:RecyclerView.Adapter<ContactListViewAdapter.MyViewHolder>(){

    private  var contactList = emptyList<ContactModel>()
    private lateinit var recyclerContactListViewBinding: RecyclerContactListViewBinding

    fun setContacts(contactList: List<ContactModel>)
    {
        this.contactList=contactList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        recyclerContactListViewBinding= DataBindingUtil.inflate(layoutInflater,R.layout.recycler_contact_list_view,parent,false)
        return MyViewHolder(recyclerContactListViewBinding)
    }

    override fun onBindViewHolder(holder:MyViewHolder, position: Int) {
        val contact = contactList[position]
        holder.bind(contact)
        holder.itemView.setOnClickListener{
            Log.d("OnClick","${contact.contactName}")
        }
    }

    override fun getItemCount(): Int {
        return contactList.count()
    }

    inner class MyViewHolder(private val recyclerContactListViewBinding: RecyclerContactListViewBinding):RecyclerView.ViewHolder(recyclerContactListViewBinding.root)
    {
        fun bind(contactModel: ContactModel) {
            recyclerContactListViewBinding.contactDetails=contactModel

        }


    }




}