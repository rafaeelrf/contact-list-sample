package com.contact_list.ui.create_contact;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.contact_list.R;
import com.contact_list.application.ContactListApplication;
import com.contact_list.db.Contact;
import com.contact_list.model.AddressResponse;
import com.contact_list.utils.Constants;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import javax.inject.Inject;


public class CreateContactActivity extends AppCompatActivity implements CreateContactView {

    @Inject
    CreateContactPresenter presenter;

    private EditText contactNameInputText;
    private EditText contactAgeInputText;
    private EditText contactPhoneInputText;
    private EditText zipcodeInputText;
    private EditText streetInputText;
    private EditText numberInputText;
    private EditText neighboorhoodInputText;
    private EditText stateInputText;
    private EditText cityInputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);

        ((ContactListApplication) getApplication()).contactListComponent.inject(this);
        presenter.setView(this);

        contactNameInputText = findViewById(R.id.nameInputText);
        contactAgeInputText = findViewById(R.id.ageInputText);
        contactPhoneInputText = findViewById(R.id.phoneInputText);
        zipcodeInputText = findViewById(R.id.zipcodeInputText);
        streetInputText = findViewById(R.id.streetInputText);
        numberInputText = findViewById(R.id.numberInputText);
        neighboorhoodInputText = findViewById(R.id.neighboorhoodInputText);
        stateInputText = findViewById(R.id.stateInputText);
        cityInputText = findViewById(R.id.cityInputText);

        TextView mToolbarTitle = findViewById(R.id.toolbarTitleTextView);
        MaterialButton createContactButton = findViewById(R.id.createContactButton);
        ImageButton mToolbarRightButton = findViewById(R.id.rightImageButton);
        ImageButton backpressImageButton = findViewById(R.id.backpressImageButton);

        backpressImageButton.setVisibility(View.VISIBLE);
        backpressImageButton.setOnClickListener(v -> goBack());
        Bundle extras = getIntent().getExtras();

        createContactButton.setOnClickListener(v -> {
            Contact contact = getContactFormData();
            if (extras != null) {
                contact.setId(extras.getInt(Constants.CONTACT_ID));
                presenter.updateContact(contact);
            } else {
                presenter.insertContact(contact);
            }
        });

        if (extras != null) {
            mToolbarTitle.setText(R.string.edit_contact_label);
            createContactButton.setText(R.string.edit_contact_label);

            Contact contact = presenter.getContactById(extras.getInt(Constants.CONTACT_ID));
            setContactFormData(contact);

            mToolbarRightButton.setVisibility(View.VISIBLE);
            mToolbarRightButton.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_remove));
            mToolbarRightButton.setOnClickListener(v -> presenter.deleteContact(
                    getIntent().getIntExtra(Constants.CONTACT_ID, 0))
            );
        } else {
            mToolbarTitle.setText(R.string.create_contact_label);
        }

        zipcodeInputText.addTextChangedListener(onZipCodeChange());
    }

    private void setContactFormData(Contact contact) {
        contactNameInputText.setText(contact.getName());
        contactAgeInputText.setText(Integer.toString(contact.getAge()));
        contactPhoneInputText.setText(contact.getPhone());
        zipcodeInputText.setText(contact.getZipcode());
        streetInputText.setText(contact.getStreet());
        numberInputText.setText(contact.getNumber());
        neighboorhoodInputText.setText(contact.getNeighborhood());
        stateInputText.setText(contact.getState());
        cityInputText.setText(contact.getCity());
    }

    @Override
    public void goBack() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void showInputState(@IdRes int viewId, boolean isValid, @StringRes int errorText) {
        TextInputLayout contactInputLayout = findViewById(viewId);
        if (isValid){
            contactInputLayout.setError(null);
        } else {
            contactInputLayout.setError(getString(errorText));
        }
    }

    @Override
    public void fillAddressData(AddressResponse address) {
        streetInputText.setText(address.getStreet());
        neighboorhoodInputText.setText(address.getNeighborhood());
        stateInputText.setText(address.getState());
        cityInputText.setText(address.getCity());
    }

    private Contact getContactFormData() {
        Contact contact = new Contact();
        contact.setName(contactNameInputText.getText().toString());
        String age = contactAgeInputText.getText().toString();
        if (!age.isEmpty()) {
            contact.setAge((Integer.parseInt(contactAgeInputText.getText().toString())));
        } else {
            contact.setAge(0);
        }
        contact.setPhone(contactPhoneInputText.getText().toString());
        contact.setZipcode(zipcodeInputText.getText().toString());
        contact.setStreet(streetInputText.getText().toString());
        contact.setNumber(numberInputText.getText().toString());
        contact.setNeighborhood(neighboorhoodInputText.getText().toString());
        contact.setState(stateInputText.getText().toString());
        contact.setCity(cityInputText.getText().toString());

        return contact;
    }

    private TextWatcher onZipCodeChange() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                String cep = s.toString();
                if (cep.length() == 8) {
                     presenter.getAddressData(cep);
                }
            }
        };
    }
}
