package com.contact_list.ui.home;

import com.contact_list.db.Contact;
import com.contact_list.repository.ContactRepository;

import java.util.List;

import javax.inject.Inject;

public class HomePresenterImpl implements HomePresenter {

    private ContactRepository mContactRepository;
    private HomeView homeView;

    @Inject
    public HomePresenterImpl(ContactRepository contactRepository) {
        this.mContactRepository = contactRepository;
    }

    @Override
    public void setView(HomeView homeView) {
        this.homeView = homeView;
    }

    @Override
    public void getAllContacts() {
        List<Contact> contacts =  mContactRepository.getAllContacts();
        homeView.showContacts(contacts);
    }
}
