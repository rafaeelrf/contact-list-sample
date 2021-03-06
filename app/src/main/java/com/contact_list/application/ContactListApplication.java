package com.contact_list.application;

import android.app.Application;

import com.contact_list.di.AppComponent;
import com.contact_list.di.AppModule;
import com.contact_list.di.DaggerAppComponent;

import io.reactivex.plugins.RxJavaPlugins;

public class ContactListApplication extends Application {

    public AppComponent contactListComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        RxJavaPlugins.setErrorHandler(throwable -> {});
        contactListComponent = initDagger(this);
    }

    private AppComponent initDagger(ContactListApplication app) {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(app))
                .build();
    }
}
