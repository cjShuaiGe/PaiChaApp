package com.example.paichaapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.paichaapp.R;
import com.example.paichaapp.RemoteControlActivity;
import com.example.paichaapp.TimeSwitchActivity;
import com.tencent.mmkv.MMKV;

public class ControlFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_control,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MMKV.initialize(getActivity());
        MMKV mmkv = MMKV.mmkvWithID("control");
        if (mmkv.decodeString("open?")==null){
            mmkv.encode("open?","yes");
            for (int i = 1; i < 4; i++) {
                mmkv.encode("portControl" + i,"端口" + i);
                mmkv.encode("switchControl" + i,"off");
        }
        }
        if (!mmkv.decodeString("open?").equals("yes")) {
            mmkv.encode("open?","yes");
            for (int i = 1; i < 4; i++) {
                mmkv.encode("portControl" + i,"端口" + i);
                mmkv.encode("switchControl" + i,"off");
            }
        }

        LinearLayout remoteControlImageView = getActivity().findViewById(R.id.remote_control);
        LinearLayout timeSwitchImageView = getActivity().findViewById(R.id.time_switch);

        remoteControlImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RemoteControlActivity.class);
                startActivity(intent);
            }
        });

        timeSwitchImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TimeSwitchActivity.class);
                startActivity(intent);
            }
        });

    }
}
