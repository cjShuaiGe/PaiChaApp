package com.example.paichaapp;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.example.paichaapp.LiveData.MyLiveData;

import org.json.JSONObject;

public class AlarmService extends Service {

    private int isAlarm = 0;
    private String cause;
    private AlarmBinder alarmBinder = new AlarmBinder();

    class AlarmBinder extends Binder {

        public int Alarm() { return isAlarm; }

        public String Cause() { return cause; }

    }

    public AlarmService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return alarmBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                MyLiveData.getMessageData().observe((LifecycleOwner) getApplicationContext(), new Observer<String>() {

                    @SuppressLint("LongLogTag")
                    @Override
                    public void onChanged(String s) {
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            if ("1".equals(jsonObject.getString("option"))) {
                                cause = jsonObject.getString("cause");
                                isAlarm = 1;
                            } else {
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                });
            }
        }).start();

        Intent intent1 = new Intent(AlarmService.this,MainActivity.class);
        startActivity(intent1);

        return super.onStartCommand(intent, flags, startId);
    }
}