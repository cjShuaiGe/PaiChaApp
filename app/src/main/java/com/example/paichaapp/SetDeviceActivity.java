package com.example.paichaapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.paichaapp.viewmodel.PortModel;
import com.tencent.mmkv.MMKV;

public class SetDeviceActivity extends AppCompatActivity {
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch sw;
    Toolbar toolbar;
    RelativeLayout relativeLayout;
    TextView device_name;
    AlertDialog setNameDialog;
    Intent intent;
    MMKV mmkv;
    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_device_activtiy);
        relativeLayout=findViewById(R.id.change_device_id);
        sw=findViewById(R.id.sw);
        toolbar=findViewById(R.id.toolbar_set_device);
        device_name=findViewById(R.id.device_name);
        mmkv=MMKV.mmkvWithID("id");
        intent=getIntent();
        s=intent.getStringExtra("deviceName");
        device_name.setText(mmkv.decodeString(s));
//        setDialog();
        setClick();
    }

//    private void setDialog() {
//        View view = View.inflate(SetDeviceActivity.this, R.layout.dialog_create, null);
//        EditText et_setName = view.findViewById(R.id.setName);
//         setNameDialog = new AlertDialog.Builder(this)
//                .setView(view)
//                .setPositiveButton("确定", (dialog, which) -> {
//                    device_name.setText(et_setName.getText().toString());
//                     mmkv.encode(s,et_setName.getText().toString());
//                    dialog.dismiss();
//                })
//                .setNegativeButton("取消", (dialog, which) -> dialog.dismiss())
//                .create();
////        setNameDialog.show();
//    }

    private void setClick() {
//        relativeLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               setNameDialog.show();
//            }
//        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}