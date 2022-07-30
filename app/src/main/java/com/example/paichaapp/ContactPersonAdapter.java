package com.example.paichaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.paichaapp.model.ContactPerson;

import java.util.List;

public class ContactPersonAdapter extends ArrayAdapter<ContactPerson> {

    private int resourceId;

    public ContactPersonAdapter(Context context, int textViewResourceId, List<ContactPerson> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ContactPerson contactPerson = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView contactPersonName = (TextView) view.findViewById(R.id.contact_person_person_name);
        TextView contactPersonUserName = (TextView) view.findViewById(R.id.contact_person_user_name);
        contactPersonName.setText(contactPerson.getPersonName());
        contactPersonUserName.setText(contactPerson.getUserName());
        return view;
    }

}
