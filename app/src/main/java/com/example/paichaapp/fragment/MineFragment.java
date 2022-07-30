package com.example.paichaapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.paichaapp.AlarmSettingsActivity;
import com.example.paichaapp.ContactPersonActivity;
import com.example.paichaapp.LiveData.MyLiveData;
import com.example.paichaapp.LoginActivity;
import com.example.paichaapp.R;
import com.example.paichaapp.UserInformationActivity;
import com.example.paichaapp.model.Response;
import com.google.gson.Gson;

public class MineFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_mine,container,false);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView nameTextView = (TextView) getActivity().findViewById(R.id.person_name);
        TextView userNameTextView = (TextView) getActivity().findViewById(R.id.user_name);
        LinearLayout userInformationLinearLayout = (LinearLayout) getActivity().findViewById(R.id.user_information);
        LinearLayout contactPersonLinearLayout = (LinearLayout) getActivity().findViewById(R.id.contact_person);
        LinearLayout alarmSettingsLinearLayout = (LinearLayout) getActivity().findViewById(R.id.alarm_settings);
        Button button = (Button) getActivity().findViewById(R.id.login_off);
        Intent intent=getActivity().getIntent();
        nameTextView.setText(intent.getStringExtra("name"));
        userNameTextView.setText(intent.getStringExtra("userName"));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.putExtra("mine","off");
                startActivity(intent);
            }
        });

        userInformationLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UserInformationActivity.class);
                intent.putExtra("name",nameTextView.getText().toString());
                intent.putExtra("userName",userNameTextView.getText().toString());
                startActivity(intent);
            }
        });

        contactPersonLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ContactPersonActivity.class);
                startActivity(intent);
            }
        });

        alarmSettingsLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AlarmSettingsActivity.class);
                startActivity(intent);
            }
        });

    }
}
