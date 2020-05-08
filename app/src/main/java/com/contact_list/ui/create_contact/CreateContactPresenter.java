package com.contact_list.ui.create_contact;

import com.contact_list.db.Contact;

public interface CreateContactPresenter {
    void insertContact(Contact contact);
    void updateContact(Contact contact);
    void deleteContact(int contactId);
    void setView(CreateContactView createContactView);
    void getAddressData(String cep);
    Contact getContactById(int contactId);
}
