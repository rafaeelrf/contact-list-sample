package com.contact_list.application;

import android.app.Application;

import com.contact_list.dagger.AppComponent;
import com.contact_list.dagger.AppModule;
import com.contact_list.dagger.DaggerAppComponent;

public class ContactListApplication extends Application {

    public AppComponent contactListComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        contactListComponent = initDagger(this);
    }

    private AppComponent initDagger(ContactListApplication app) {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(app))
                .build();
    }
}
