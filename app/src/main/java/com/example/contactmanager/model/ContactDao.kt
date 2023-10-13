package com.example.contactmanager.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ContactDao {


    @Insert()
    suspend fun insertContact(contact: ContactModel)

    @Query("UPDATE ContactsDetails SET contactName = :name, contactNumber = :phNumber, contactImage = :image WHERE contactId =:id")
    suspend fun updatebyId(name: String, phNumber: String, id: Int, image: String)

    @Query("DELETE FROM ContactsDetails WHERE contactId = :id")
    suspend fun deleteById(id: Int)

    @Query("DELETE FROM ContactsDetails")
    suspend fun deleteAll()

    @Query("SELECT * FROM ContactsDetails ORDER BY contactName ASC ")
    fun readAllData(): LiveData<List<ContactModel>>


}
