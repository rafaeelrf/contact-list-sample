package com.contact_list.ui.home;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.contact_list.R;
import com.contact_list.db.Contact;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private List<Contact> mContacts;
    private EventListener mListener;
    private Context mContext;

    ContactAdapter(Context context, List<Contact> contacts, EventListener listener) {
        this.mContext = context;
        this.mContacts = contacts;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_item, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = mContacts.get(position);
        holder.contactName.setText(contact.getName());
        holder.contactPhone.setText(contact.getPhone());
        holder.contactPhone.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + contact.getPhone()));
            mContext.startActivity(intent);
        });
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

    public interface EventListener {
        void showEditContactScreen(Contact contact);
    }

}
