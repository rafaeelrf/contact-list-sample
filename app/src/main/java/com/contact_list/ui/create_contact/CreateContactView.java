package com.contact_list.ui.create_contact;

public interface CreateContactView {
    void goBack();

    void showInputNameState(boolean isValid);

    void showInputAgeState(boolean isValid);

    void showInputPhoneState(boolean isValid);
}
