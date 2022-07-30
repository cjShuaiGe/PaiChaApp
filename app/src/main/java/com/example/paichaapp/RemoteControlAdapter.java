package com.example.paichaapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.paichaapp.Util.Util;
import com.example.paichaapp.model.Switchs;
import com.tencent.mmkv.MMKV;

import java.util.List;

public class RemoteControlAdapter extends ArrayAdapter<Switchs> {

    private int resourceId;

    public RemoteControlAdapter(Context context, int textViewResourceId, List<Switchs> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        MMKV.initialize(getContext());
        MMKV mmkv = MMKV.mmkvWithID("control");
        Switchs s = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView portControl = (TextView) view.findViewById(R.id.remote_control_port_name);
        portControl.setText(s.getPortName());
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch portSwitch = (Switch) view.findViewById(R.id.port_switch);
        if (s.getSwitchControl().equals("on")) {
            portSwitch.setChecked(true);
        } else {
            portSwitch.setChecked(false);
        }

        portSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!b) {
                    int i = 1;
                    for ( ; i < 4; i++) {
                        if (mmkv.decodeString("portControl" + i).equals(s.getPortName())) {
                            mmkv.encode("switchControl" + i, "on");
                            Util.sendMessage("{\"option\":\"1\",\"key\":\"switch\",\"value\":\"ON\",\"index_num\":" +"\""+ i + "\""+"}");
                        }
                    }
                } else {
                    int i = 1;
                    for ( ; i < 4; i++) {
                        if (mmkv.decodeString("portControl" + i).equals(s.getPortName())) {
                            mmkv.encode("switchControl" + "\""+i+"\"", "off");
                            Util.sendMessage("{\"option\":\"1\",\"key\":\"switch\",\"value\":\"OFF\",\"index_num\":" +"\""+ i + "\""+"}");
                        }
                    }
                }
            }
        });

        return view;
    }

    protected void onResult() {
    }

}
