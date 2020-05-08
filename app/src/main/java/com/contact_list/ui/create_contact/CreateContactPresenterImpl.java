package com.contact_list.ui.create_contact;

import com.contact_list.R;
import com.contact_list.db.Contact;
import com.contact_list.interactors.GetAddressInteractor;
import com.contact_list.model.AddressResponse;
import com.contact_list.repository.ContactRepository;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class CreateContactPresenterImpl implements CreateContactPresenter {

    private ContactRepository mContactRepository;
    private CreateContactView createContactView;
    private GetAddressInteractor getAddressInteractor;
    private Disposable addressDisposable;


    @Inject
    public CreateContactPresenterImpl(ContactRepository contactRepository, GetAddressInteractor getAddressInteractor) {
        this.mContactRepository = contactRepository;
        this.getAddressInteractor = getAddressInteractor;
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

    @Override
    public void getAddressData(String cep) {
        if (addressDisposable != null) {
            addressDisposable.dispose();
        }

        addressDisposable = getAddressInteractor.invoke(cep)
                .subscribe(this::handleSuccess, this::handleException);
    }

    @Override
    public Contact getContactById(int contactId) {
        return mContactRepository.getContactById(contactId);
    }

    private void handleSuccess(AddressResponse address) {
        createContactView.fillAddressData(address);
    }

    private void handleException(Throwable t) { }

    private boolean isInvalidContact(Contact contact) {
        createContactView.showInputState(R.id.nameInputTextLayout, !contact.getName().isEmpty(), R.string.name_required_label);
        createContactView.showInputState(R.id.ageInputTextLayout,contact.getAge() > 0, R.string.age_required_label);
        createContactView.showInputState(R.id.phoneInputTextLayout, !contact.getPhone().isEmpty(), R.string.phone_required_label);
        createContactView.showInputState(R.id.zipcodeInputTextLayout, contact.getZipcode().length() == 8, R.string.contact_cep_required);
        createContactView.showInputState(R.id.streetInputTextLayout, !contact.getStreet().isEmpty(), R.string.contact_street_required);
        createContactView.showInputState(R.id.numberInputTextLayout, !contact.getNumber().isEmpty(), R.string.contact_number_required);
        createContactView.showInputState(R.id.neighboorhoodInputTextLayout, !contact.getNeighborhood().isEmpty(), R.string.contact_neighborhood_required);
        createContactView.showInputState(R.id.stateInputTextLayout, !contact.getState().isEmpty(), R.string.contact_state_required);
        createContactView.showInputState(R.id.cityInputTextLayout, !contact.getCity().isEmpty(), R.string.contact_city_required);

        return contact.getName().isEmpty() ||
                contact.getAge() <= 0 ||
                contact.getPhone().isEmpty() ||
                contact.getZipcode().length() != 8 ||
                contact.getStreet().isEmpty() ||
                contact.getNumber().isEmpty() ||
                contact.getNeighborhood().isEmpty() ||
                contact.getState().isEmpty() ||
                contact.getCity().isEmpty();
    }
}
