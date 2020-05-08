package com.contact_list.ui.create_contact;

import androidx.annotation.IdRes;
import androidx.annotation.StringRes;

import com.contact_list.model.AddressResponse;

public interface CreateContactView {
    void goBack();

    void showInputState(@IdRes int viewId, boolean isValid, @StringRes int errorText);

    void fillAddressData(AddressResponse address);

}
