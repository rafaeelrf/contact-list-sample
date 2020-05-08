package com.contact_list.ui.home;

import com.contact_list.db.Contact;

import java.util.List;

public interface HomeView {
    void showContacts(List<Contact> contacts);
}