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

public class ContactPersonActivity extends AppCompatActivity {

    private List<ContactPerson> contactPersonList = new ArrayList<>();

    //刷新每次传入的Intent
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_contact_person);
        MMKV.initialize(this);
        contactPersonList.clear();
        ImageView backImageView = (ImageView) findViewById(R.id.contact_person_back);
        ImageView addImageView = (ImageView) findViewById(R.id.contact_person_add);

        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(ContactPersonActivity.this,MainActivity.class);
//                startActivity(intent);
                finish();
            }
        });

        addImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactPersonActivity.this,ContactPersonAddActivity.class);
                startActivity(intent);
            }
        });

        MMKV mmkv = MMKV.mmkvWithID("contactPerson");
        int i = 1;
        for ( ; mmkv.decodeString("person" + i) != null ; i++ ) {
            contactPersonList.add(new ContactPerson(mmkv.decodeString("person" + i),mmkv.decodeString("phone" + i),mmkv.decodeString("switch" + i)));
        }
        ContactPersonAdapter adapter = new ContactPersonAdapter(ContactPersonActivity.this, R.layout.contact_person_item,contactPersonList);
        ListView listView = (ListView) findViewById(R.id.contact_person_listview);
        listView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        if ("ok".equals(intent.getStringExtra("add"))) {
            MMKV mmkv = MMKV.mmkvWithID("contactPerson");
            int i = 1;
            for ( ; mmkv.decodeString("person" + i) != null ; i++ ) {
            }
            mmkv.encode("person" + i,intent.getStringExtra("personName"));
            mmkv.encode("phone" + i,intent.getStringExtra("userName"));
            mmkv.encode("switch" + i,"off");
        } else {
        }
    }

}