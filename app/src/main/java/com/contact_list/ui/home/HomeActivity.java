package com.contact_list.ui.home;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.contact_list.R;
import com.contact_list.application.ContactListApplication;
import com.contact_list.db.Contact;
import com.contact_list.ui.create_contact.CreateContactActivity;
import com.contact_list.utils.Constants;

import java.util.List;

import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity implements HomeView, View.OnClickListener, ContactAdapter.EventListerner {

    @Inject HomePresenter presenter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((ContactListApplication) getApplication()).contactListComponent.inject(this);

        TextView mToolbarTitle = findViewById(R.id.toolbarTitleTextView);
        mToolbarTitle.setText(R.string.contacts_title);

        ImageButton mToolbarRightButton = findViewById(R.id.rightImageButton);
        mToolbarRightButton.setVisibility(View.VISIBLE);
        mToolbarRightButton.setOnClickListener(this);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.addItemDecoration(new ContactDecoration(getApplicationContext()));

        presenter.setView(this);
        presenter.getAllContacts();
    }

    @Override
    public void showContacts(List<Contact> contacts) {
        mRecyclerView.setAdapter(new ContactAdapter(contacts, this));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rightImageButton:
                Intent intent = new Intent(this, CreateContactActivity.class);
                startActivityForResult(intent, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                presenter.getAllContacts();
            }
        }

    }

    @Override
    public void showEditContactScreen(Contact contact) {
        Intent intent = new Intent(this, CreateContactActivity.class);
        intent.putExtra(Constants.CONTACT_ID, contact.getId());
        startActivityForResult(intent, 1);
    }
}
