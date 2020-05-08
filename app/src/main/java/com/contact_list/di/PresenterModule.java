package com.contact_list.di;

import com.contact_list.interactors.GetAddressInteractor;
import com.contact_list.repository.ContactRepository;
import com.contact_list.ui.create_contact.CreateContactPresenter;
import com.contact_list.ui.create_contact.CreateContactPresenterImpl;
import com.contact_list.ui.home.HomePresenter;
import com.contact_list.ui.home.HomePresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class PresenterModule {

    @Provides
    @Singleton
    HomePresenter provideHomePresenter(ContactRepository contactRepository) {
        return new HomePresenterImpl(contactRepository);
    }

    @Provides
    @Singleton
    CreateContactPresenter provideCreateContactPresenter(ContactRepository contactRepository, GetAddressInteractor getAddressInteractor) {
        return new CreateContactPresenterImpl(contactRepository, getAddressInteractor);
    }
}
