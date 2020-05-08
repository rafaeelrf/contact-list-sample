package com.contact_list.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.contact_list.R;
import com.contact_list.db.Contact;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private List<Contact> mContacts;
    private EventListerner mListener;

    ContactAdapter(List<Contact> mContacts, EventListerner mListener) {
        this.mContacts = mContacts;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_item, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = mContacts.get(position);
        holder.contactName.setText(contact.getName());
        holder.contactPhone.setText(contact.getPhone());
        holder.removeContactButton.setOnClickListener(v -> mListener.showEditContactScreen(contact));
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    static class ContactViewHolder extends RecyclerView.ViewHolder {

        TextView contactName;
        TextView contactPhone;
        ImageButton removeContactButton;

        ContactViewHolder(View v) {
            super(v);
            contactName = v.findViewById(R.id.contactNameTextView);
            contactPhone = v.findViewById(R.id.contactPhoneTextView);
            removeContactButton = v.findViewById(R.id.removeContactButton);
        }

    }

    public interface EventListerner {
        void showEditContactScreen(Contact contact);
    }

}
