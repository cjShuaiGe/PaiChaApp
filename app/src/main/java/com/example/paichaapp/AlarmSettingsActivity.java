package com.example.paichaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.paichaapp.model.ContactPerson;
import com.tencent.mmkv.MMKV;

import java.util.ArrayList;
import java.util.List;

public class AlarmSettingsActivity extends AppCompatActivity {

    private List<ContactPerson> contactPersonList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_alarm_settings);
        MMKV.initialize(this);
        contactPersonList.clear();
        ImageView backImageView = (ImageView) findViewById(R.id.alarm_settings_back);

        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });

        MMKV mmkv = MMKV.mmkvWithID("contactPerson");
        int i = 1;
        for ( ; mmkv.decodeString("person" + i) != null ; i++ ) {
            contactPersonList.add(new ContactPerson(mmkv.decodeString("person" + i),mmkv.decodeString("phone" + i),mmkv.decodeString("switch" + i)));
        }
        AlarmSettingsAdapter adapter = new AlarmSettingsAdapter(AlarmSettingsActivity.this, R.layout.alarm_settings_item,contactPersonList);
        ListView listView = (ListView) findViewById(R.id.alarm_settings_listview);
        listView.setAdapter(adapter);

    }
}