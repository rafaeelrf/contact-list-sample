package com.contact_list.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactDao {

    @Insert
    void insert(Contact contact);

    @Query("DELETE FROM contact WHERE id = :contactId")
    void deleteContact(int contactId);

    @Update
    void updateContact(Contact contact);

    @Query("SELECT * FROM contact ORDER BY name ASC")
    List<Contact> getAllContacts();

    @Query("SELECT * FROM contact WHERE id = :contactId")
    Contact getContactById(int contactId);

}
