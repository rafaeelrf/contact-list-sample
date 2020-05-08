package com.contact_list.di;

import com.contact_list.api.APIInterface;
import com.contact_list.interactors.GetAddressInteractor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ProvideInteractors {

    @Provides
    @Singleton
    GetAddressInteractor provideGetAddressInteractor(APIInterface apiInterface) {
        return new GetAddressInteractor(apiInterface);
    }
}
