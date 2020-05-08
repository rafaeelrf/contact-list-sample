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

import javax.inject.Inject;


public class CreateContactActivity extends AppCompatActivity implements CreateContactView, View.OnClickListener {

    @Inject
    CreateContactPresenter presenter;

    private TextView name;
    private TextView age;
    private TextView phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);

        ((ContactListApplication) getApplication()).contactListComponent.inject(this);

        TextView mToolbarTitle = findViewById(R.id.toolbarTitleTextView);
        MaterialButton createContactButton = findViewById(R.id.createContactButton);
        ImageButton mToolbarRightButton = findViewById(R.id.rightImageButton);
        ImageButton backpressImageButton = findViewById(R.id.backpressImageButton);
        name = findViewById(R.id.nameInputText);
        age = findViewById(R.id.ageInputText);
        phone = findViewById(R.id.phoneInputText);

        backpressImageButton.setVisibility(View.VISIBLE);
        backpressImageButton.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();

        mToolbarRightButton.setOnClickListener(this);

        if (extras != null) {
            mToolbarTitle.setText(R.string.edit_contact_label);
            createContactButton.setText(R.string.edit_contact_label);

            name.setText(extras.getString(Constants.CONTACT_NAME));
            age.setText(extras.getString(Constants.CONTACT_AGE));
            phone.setText(extras.getString(Constants.CONTACT_PHONE));

            mToolbarRightButton.setVisibility(View.VISIBLE);
            mToolbarRightButton.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_remove));
        } else {
            mToolbarTitle.setText(R.string.create_contact_label);
        }

        createContactButton.setOnClickListener(this);
    }

    @Override
    public void goBack() {
        setResult(RESULT_OK);
        finish();
    }

    private Contact getContactData() {
        Contact contact = new Contact();
        contact.setName(name.getText().toString());
        contact.setAge((Integer.parseInt(age.getText().toString())));
        contact.setPhone(phone.getText().toString());

        return contact;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.createContactButton:
                Contact contact = getContactData();
                if (getIntent().getExtras() != null) {
                    presenter.updateContact(contact);
                } else {
                    presenter.insertContact(contact);
                }
            case R.id.rightImageButton:
                presenter.deleteContact(getIntent().getIntExtra(Constants.CONTACT_ID, 0));
            case R.id.backpressImageButton:
                goBack();
        }
    }
}
