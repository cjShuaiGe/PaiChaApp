package com.example.paichaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
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
    private AlarmService.AlarmBinder alarmBinder;
    private int isAlarmOk = 0;
    private String cause;

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
        Intent startServiceIntent = new Intent(MainActivity.this, AlarmService.class);
        startService(startServiceIntent);
        Intent bindIntent = new Intent(MainActivity.this,AlarmService.class);
        bindService(bindIntent,alarmConnection, Context.BIND_AUTO_CREATE);   //绑定服务
    }
    private ServiceConnection alarmConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            alarmBinder = (AlarmService.AlarmBinder) iBinder;
            while (isAlarmOk == 0) {
                isAlarmOk = alarmBinder.Alarm();
                cause = alarmBinder.Cause();
            }
            isAlarmOk = 0;
            MMKV.initialize(MainActivity.this);
            MMKV mmkv = MMKV.mmkvWithID("contactPerson");
            int i = 1;
            for ( ; mmkv.decodeString("person" + i) != null; i++) {
                if (mmkv.decodeString("switch" + i).equals("on")) {
                    Intent intent = new Intent();
                    intent.setAction(intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("smsto:" + mmkv.decodeString("phone" + i)));
                    intent.putExtra("sms_body","警报！排插端口异常！");
                    startActivity(intent);
                }
            }
            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
            dialog.setTitle("警报");
            dialog.setMessage("收到端口警报：" + cause);
            dialog.setCancelable(false);
            dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
        }
    };

    //刷新每次传入的Intent
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
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