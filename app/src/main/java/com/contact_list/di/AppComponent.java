package com.contact_list.di;

import com.contact_list.ui.create_contact.CreateContactActivity;
import com.contact_list.ui.home.HomeActivity;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {
        AppModule.class,
        PresenterModule.class
})
public interface AppComponent {
    void inject(HomeActivity target);
    void inject(CreateContactActivity target);
}
