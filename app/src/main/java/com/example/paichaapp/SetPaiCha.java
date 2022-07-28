package com.example.paichaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.paichaapp.LiveData.MyLiveData;
import com.example.paichaapp.model.Option;
import com.example.paichaapp.Util.Util;
import com.example.paichaapp.model.Receive;
import com.google.gson.Gson;

public class SetPaiCha extends AppCompatActivity {
   Button bt_add;
   Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_pai_cha);
        bt_add=findViewById(R.id.bt_add_paicha);
        toolbar=findViewById(R.id.toolbar_set_paicha);
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SetPaiCha.this,AddPaiCha.class));
            }
        });
       toolbar.setNavigationOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
           }
       });
    }
}