package com.contact_list.ui.create_contact;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.contact_list.R;
import com.contact_list.application.ContactListApplication;
import com.contact_list.db.Contact;
import com.contact_list.utils.Constants;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import javax.inject.Inject;


public class CreateContactActivity extends AppCompatActivity implements CreateContactView {

    @Inject
    CreateContactPresenter presenter;

    private TextView contactNameInput;
    private TextView contactAgeInput;
    private TextView contactPhoneInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);

        ((ContactListApplication) getApplication()).contactListComponent.inject(this);
        presenter.setView(this);

        TextView mToolbarTitle = findViewById(R.id.toolbarTitleTextView);
        MaterialButton createContactButton = findViewById(R.id.createContactButton);
        ImageButton mToolbarRightButton = findViewById(R.id.rightImageButton);
        ImageButton backpressImageButton = findViewById(R.id.backpressImageButton);
        contactNameInput = findViewById(R.id.nameInputText);
        contactAgeInput = findViewById(R.id.ageInputText);
        contactPhoneInput = findViewById(R.id.phoneInputText);

        backpressImageButton.setVisibility(View.VISIBLE);
        backpressImageButton.setOnClickListener(v -> goBack());

        mToolbarRightButton.setOnClickListener(v -> presenter.deleteContact(
                getIntent().getIntExtra(Constants.CONTACT_ID, 0))
        );

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            mToolbarTitle.setText(R.string.edit_contact_label);
            createContactButton.setText(R.string.edit_contact_label);

            contactNameInput.setText(extras.getString(Constants.CONTACT_NAME));
            contactAgeInput.setText(extras.getString(Constants.CONTACT_AGE));
            contactPhoneInput.setText(extras.getString(Constants.CONTACT_PHONE));

            mToolbarRightButton.setVisibility(View.VISIBLE);
            mToolbarRightButton.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_remove));
        } else {
            mToolbarTitle.setText(R.string.create_contact_label);
        }

        createContactButton.setOnClickListener(v -> {
            Contact contact = getContactData();
            if (extras != null) {
                contact.setId(extras.getInt(Constants.CONTACT_ID));
                presenter.updateContact(contact);
            } else {
                presenter.insertContact(contact);
            }
        });
    }

    @Override
    public void goBack() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void showInputNameState(boolean isValid) {
        TextInputLayout contactNameInputLayout = findViewById(R.id.nameInputTextLayout);
        if (isValid) {
            contactNameInputLayout.setError(null);
        } else {
            contactNameInputLayout.setError(getString(R.string.name_required_label));
        }
    }

    @Override
    public void showInputAgeState(boolean isValid) {
        TextInputLayout contactAgeInputLayout = findViewById(R.id.ageInputTextLayout);
        if (isValid) {
            contactAgeInputLayout.setError(null);
        } else {
            contactAgeInputLayout.setError(getString(R.string.age_required_label));
        }
    }

    @Override
    public void showInputPhoneState(boolean isValid) {
        TextInputLayout contactPhoneInputLayout = findViewById(R.id.phoneInputTextLayout);
        if (isValid){
            contactPhoneInputLayout.setError(null);
        } else {
            contactPhoneInputLayout.setError(getString(R.string.phone_required_label));
        }
    }

    private Contact getContactData() {
        Contact contact = new Contact();
        contact.setName(contactNameInput.getText().toString());
        String age = contactAgeInput.getText().toString();
        if (!age.isEmpty()) {
            contact.setAge((Integer.parseInt(contactAgeInput.getText().toString())));
        } else {
            contact.setAge(0);
        }
        contact.setPhone(contactPhoneInput.getText().toString());

        return contact;
    }
}
