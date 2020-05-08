package com.contact_list.ui.create_contact;

import com.contact_list.db.Contact;
import com.contact_list.repository.ContactRepository;

import javax.inject.Inject;

public class CreateContactPresenterImpl implements CreateContactPresenter {

    private ContactRepository mContactRepository;
    private CreateContactView createContactView;

    @Inject
    public CreateContactPresenterImpl(ContactRepository contactRepository) {
        this.mContactRepository = contactRepository;
    }

    @Override
    public void insertContact(Contact contact) {
        boolean isValid = !isInvalidContact(contact);
        if (isValid) {
            mContactRepository.insert(contact);
            createContactView.goBack();
        }
    }

    @Override
    public void updateContact(Contact contact) {
        boolean isValid = !isInvalidContact(contact);
        if (isValid) {
            mContactRepository.updateContact(contact);
            createContactView.goBack();
        }
    }

    @Override
    public void deleteContact(int contactId) {
        mContactRepository.deleteContact(contactId);
        createContactView.goBack();
    }

    @Override
    public void setView(CreateContactView createContactView) {
        this.createContactView = createContactView;
    }

    private boolean isInvalidContact(Contact contact) {
        boolean hasError = false;
        createContactView.showInputNameState(!contact.getName().isEmpty());
        createContactView.showInputAgeState(contact.getAge() > 0);
        createContactView.showInputPhoneState(!contact.getPhone().isEmpty());

        return contact.getName().isEmpty() || contact.getAge() <= 0 || contact.getPhone().isEmpty();
    }
}
