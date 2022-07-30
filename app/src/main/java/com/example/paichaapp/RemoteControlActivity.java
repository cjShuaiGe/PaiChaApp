package com.example.paichaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.paichaapp.model.Switchs;
import com.tencent.mmkv.MMKV;

import java.util.ArrayList;
import java.util.List;

public class RemoteControlActivity extends AppCompatActivity {

    private List<Switchs> port = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.control_remote_control);
        MMKV.initialize(this);
        MMKV mmkv = MMKV.mmkvWithID("control");
        ImageView backImageView = (ImageView) findViewById(R.id.remote_control_back);
        EditText portEditText = (EditText) findViewById(R.id.remote_control_search);
        Button searchButton = (Button) findViewById(R.id.remote_control_search_button);

        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RemoteControlActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = 1;
                for ( ; i < 4; i++) {
                    if (portEditText.getText().toString().equals(mmkv.decodeString("portControl" + i))) {
                        port.add(new Switchs(mmkv.decodeString("portControl" + i),mmkv.decodeString("switchControl" + i)));
                        break;
                    }
                }

                RemoteControlAdapter adapter = new RemoteControlAdapter(RemoteControlActivity.this, R.layout.remote_control_item,port);
                ListView listView = (ListView) findViewById(R.id.remote_control_listview);
                listView.setAdapter(adapter);
            }
        });

    }
}