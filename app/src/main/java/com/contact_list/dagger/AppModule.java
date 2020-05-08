package com.contact_list.dagger;

import android.app.Application;
import android.content.Context;

import com.contact_list.repository.ContactRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Application app;

    public AppModule(Application app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return this.app;
    }

    @Provides
    @Singleton
    public Application provideApplication() { return this.app; }

    @Provides
    @Singleton
    ContactRepository provideContactRepository(Application app) {
        return new ContactRepository(app);
    }

}
