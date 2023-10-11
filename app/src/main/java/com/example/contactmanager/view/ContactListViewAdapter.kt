package com.example.contactmanager.view

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.contactmanager.R
import androidx.recyclerview.widget.RecyclerView
import com.example.contactmanager.databinding.RecyclerContactListViewBinding
import com.example.contactmanager.model.ContactModel

class ContactListViewAdapter(context: Context):RecyclerView.Adapter<ContactListViewAdapter.MyViewHolder>(){

    private  var contactList = emptyList<ContactModel>()
    private lateinit var recyclerContactListViewBinding: RecyclerContactListViewBinding
    private val context = context

    fun setContacts(contactList: List<ContactModel>)
    {
        this.contactList=contactList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        recyclerContactListViewBinding= DataBindingUtil.inflate(layoutInflater,R.layout.recycler_contact_list_view,parent,false)
        return MyViewHolder(recyclerContactListViewBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val contact = contactList[position]
        holder.bind(contact)
        holder.itemView.setOnClickListener{
            Log.d("OnClick","$contact")
            val intent = Intent(context, ContactDetailActivity::class.java)
            intent.putExtra("name","${contact.contactName}")
            intent.putExtra("number","${contact.contactNumber}")
            intent.putExtra("id","${contact.contactId}")
            context.startActivity(intent)
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