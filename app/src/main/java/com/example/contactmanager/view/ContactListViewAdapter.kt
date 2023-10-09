package com.example.contactmanager.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import com.example.contactmanager.R
import androidx.recyclerview.widget.RecyclerView
import com.example.contactmanager.databinding.ActivityContactListBinding
import com.example.contactmanager.model.ContactModel

class ContactListViewAdapter:RecyclerView.Adapter<ContactListViewAdapter.MyViewHolder>(){

    private  var contactList = emptyList<ContactModel>()
    private lateinit var userDataBinding: ActivityContactListBinding

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
        userDataBinding= DataBindingUtil.inflate(layoutInflater,R.layout.recycler_contact_list_view,parent,false)
        return MyViewHolder(userDataBinding.recyclerContactListView)
    }

    override fun onBindViewHolder(holder:MyViewHolder, position: Int) {
        with(holder)
        {
            with(contactList[position]){
            }
        }
    }

    override fun getItemCount(): Int {
        return contactList.count()
    }

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

    }

}