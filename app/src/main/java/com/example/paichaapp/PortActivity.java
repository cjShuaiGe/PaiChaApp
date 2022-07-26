package com.example.paichaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.paichaapp.adapter.FragmentAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class PortActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_port);
        TabLayout t1=findViewById(R.id.tabs);
        ViewPager2 viewPager2=findViewById(R.id.vp2);
        viewPager2.setAdapter(new FragmentAdapter(this));
        TabLayoutMediator tab=new TabLayoutMediator(t1, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0: tab.setText("当前");
                        break;

                    case 1: tab.setText("历史");
                        break;
                    case 2: tab.setText("预测");
                        break;
                }
            }
        });
        tab.attach();
    }
}