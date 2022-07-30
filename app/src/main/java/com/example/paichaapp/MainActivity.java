package com.example.paichaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.paichaapp.Util.Util;
import com.example.paichaapp.adapter.MainFragmentAdapter;
import com.example.paichaapp.fragment.ControlFragment;
import com.example.paichaapp.fragment.EquipmentFragment;
import com.example.paichaapp.fragment.MineFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tencent.mmkv.MMKV;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private BottomNavigationView bottomNavigationView;
    private List<Fragment> fragmentList =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MMKV.initialize(this);
        viewPager=findViewById(R.id.main_vp2);
        bottomNavigationView=findViewById(R.id.bottom_navigation);
        initBottomNavigation();
    }


    private void initBottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.chat:
                        viewPager.setCurrentItem(0);

                        break;
                    case R.id.account:
                        viewPager.setCurrentItem(1);

                        break;
                    case R.id.setting:
                        viewPager.setCurrentItem(2);

                        break;
                    default:break;

                }

                return false;
            }
        });
        viewPager.setAdapter(new MainFragmentAdapter(MainActivity.this));
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }



}