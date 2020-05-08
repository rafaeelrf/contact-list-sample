package com.contact_list.ui.create_contact;

import com.contact_list.db.Contact;
import com.contact_list.repository.ContactRepository;

import javax.inject.Inject;

public class CreateContactPresenterImpl implements CreateContactPresenter {

    private ContactRepository mContactRepository;
    private CreateContactView homeView;

    @Inject
    public CreateContactPresenterImpl(ContactRepository contactRepository) {
        this.mContactRepository = contactRepository;
    }

    @Override
    public void insertContact(Contact contact) {
        mContactRepository.insert(contact);
        homeView.goBack();
    }

    @Override
    public void updateContact(Contact contact) {
        mContactRepository.updateContact(contact);
        homeView.goBack();
    }

    @Override
    public void deleteContact(int contactId) {
        mContactRepository.deleteContact(contactId);
        homeView.goBack();
    }
}
