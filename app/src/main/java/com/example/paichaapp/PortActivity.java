package com.example.paichaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.paichaapp.adapter.FragmentAdapter;
import com.example.paichaapp.viewmodel.PortModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class PortActivity extends AppCompatActivity {
    Intent intent;
    PortModel portModel;
    Toolbar toolbar;
    TextView tv_choose_port;
    ViewPager2 viewPager2;
    TabLayout t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_port);
        intent = getIntent();
        portModel = new ViewModelProvider(this).get(PortModel.class);
        t1 = findViewById(R.id.tabs);
        viewPager2 = findViewById(R.id.vp2);
        toolbar=findViewById(R.id.toolbar_device);
        tv_choose_port=findViewById(R.id.choose_port);

       saveIntentData();
       setView();

    }

    private void setView() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        viewPager2.setAdapter(new FragmentAdapter(this));
        TabLayoutMediator tab = new TabLayoutMediator(t1, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("当前");
                        break;

                    case 1:
                        tab.setText("历史");
                        break;
                    case 2:
                        tab.setText("预测");
                        break;
                }
            }
        });
        tab.attach();
    }

    private void saveIntentData() {
        portModel.refreshPortName(intent.getStringExtra("portname"));
        portModel.refreshDeviceName(intent.getStringExtra("devicename"));
        portModel.portName().observe(this, new Observer<String>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onChanged(String s) {
               tv_choose_port.setText(s);

            }
        });
    }
}