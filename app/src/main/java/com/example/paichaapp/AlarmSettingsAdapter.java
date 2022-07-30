package com.example.paichaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.paichaapp.model.ContactPerson;

import java.util.List;

public class AlarmSettingsAdapter extends ArrayAdapter<ContactPerson> {

    private int resourceId;

    public AlarmSettingsAdapter(Context context, int textViewResourceId, List<ContactPerson> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ContactPerson contactPerson = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView personName = (TextView) view.findViewById(R.id.alarm_settings_person_name);
        personName.setText(contactPerson.getPersonName());
        Switch messageSwitch = (Switch) view.findViewById(R.id.message_alarm);

        return view;
    }
}
