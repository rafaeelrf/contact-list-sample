package com.contact_list.repository;

import android.app.Application;

import com.contact_list.db.Contact;
import com.contact_list.db.ContactDao;
import com.contact_list.db.ContactRoomDatabase;

import java.util.List;

public class ContactRepository {

    private ContactDao mContactDao;
    private List<Contact> mAllContacts;

    public ContactRepository(Application application) {
        ContactRoomDatabase db = ContactRoomDatabase.getDatabase(application);
        mContactDao = db.contactDao();
    }

    public List<Contact> getAllContacts() {
        return mContactDao.getAllContacts();
    }

    public void insert(Contact contact) {
        ContactRoomDatabase.databaseWriteExecutor.execute(() -> {
            mContactDao.insert(contact);
        });
    }

    public void updateContact(Contact contact) {
        ContactRoomDatabase.databaseWriteExecutor.execute(() -> {
            mContactDao.updateContact(contact);
        });
    }

    public void deleteContact(int contactId) {
        mContactDao.deleteContact(contactId);
    }

}
