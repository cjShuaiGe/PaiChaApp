package com.example.paichaapp.fragment;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paichaapp.LiveData.MyLiveData;
import com.example.paichaapp.R;
import com.example.paichaapp.Search;
import com.example.paichaapp.SetPaiCha;
import com.example.paichaapp.Util.Util;
import com.example.paichaapp.adapter.RcPortAdapter;
import com.example.paichaapp.model.Max;
import com.example.paichaapp.model.Option;
import com.example.paichaapp.model.Port;
import com.example.paichaapp.model.Receive;
import com.example.paichaapp.viewmodel.PortModel;
import com.google.gson.Gson;
import com.tencent.mmkv.MMKV;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EquipmentFragment extends Fragment {
    RecyclerView rc;
    List<Port> mlist=new ArrayList<>();
    RcPortAdapter adapter;
    PortModel portModel;
    MMKV mmkv;
    Toolbar toolbar;
    Button bt_search;
    Gson gson=new Gson();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_equipment,container,false);
        init(view);
        initData();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SetPaiCha.class));
            }
        });
        return view;
    }


    @SuppressLint("LongLogTag")
    private void initData() {
        mlist.clear();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyyMMdd");
        Date date = new Date(System.currentTimeMillis());
        String time=formatter.format(date);
        Option option=new Option();
        option.setDate(time);option.setOption("6");
        Util.sendMessage(gson.toJson(option));
        mmkv=MMKV.mmkvWithID("id");
        MyLiveData.getMessageData().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Receive receive=gson.fromJson(s,Receive.class);
                String status1="工作";String status2="工作";String status3="工作";
                if (receive.getLabel()!=null){
                if (receive.getLabel().equals("3")){
                 if (receive.getName1().equals("UNKNOW")){
                     receive.setIndex_num1(new Max("0","0","0"));
                     status1="断电";
                 }  if (receive.getName2() .equals("UNKNOW")){
                        receive.setIndex_num2(new Max("0","0","0"));
                        status2="断电";
                }  if (receive.getName3().equals("UNKNOW")){
                        receive.setIndex_num3(new Max("0","0","0"));
                        status3="断电";
                }
                String s1="端口1:"+receive.getName1();
                String s2="端口2:"+receive.getName2();
                String s3="端口3:"+receive.getName3();
                mlist.add(new Port(s1,status1,receive.getUsepower(),receive.getIndex_num1().getCurrent(),receive.getIndex_num1().getVoltage(),
                        receive.getIndex_num1().getPower()));
                mlist.add(new Port(s2,status2,receive.getUsepower(),receive.getIndex_num2().getCurrent(),receive.getIndex_num2().getVoltage(),
                        receive.getIndex_num2().getPower()));
                mlist.add(new Port(s3,status3,receive.getUsepower(),receive.getIndex_num3().getCurrent(),receive.getIndex_num3().getVoltage(),
                        receive.getIndex_num3().getPower()));
                for (int i=1;i<=3;i++){
                    mmkv.encode("usePower"+i,receive.getUsepower());
                }
                mmkv.encode("dk1",s1);mmkv.encode("dk2",s2);mmkv.encode("dk3",s3);
                mmkv.encode("status1",status1);mmkv.encode("status2",status2);mmkv.encode("status3",status3);
                mmkv.encode("current1",receive.getIndex_num1().getCurrent());mmkv.encode("current2",receive.getIndex_num2().getCurrent());mmkv.encode("current3",receive.getIndex_num3().getCurrent());
                mmkv.encode("voltage1",receive.getIndex_num1().getVoltage());mmkv.encode("voltage2",receive.getIndex_num2().getVoltage());mmkv.encode("voltage3",receive.getIndex_num3().getVoltage());
                mmkv.encode("power1",receive.getIndex_num1().getPower());mmkv.encode("power2",receive.getIndex_num2().getPower());mmkv.encode("power3",receive.getIndex_num3().getPower());
                portModel.refreshPortsData(mlist);
                }
                }
            }
        });
        portModel.refreshPortsData(mlist);
    }


    private void init(View view) {
//        mmkv=MMKV.mmkvWithID("id");
//        for (int i=1;i<=5;i++){
//            String s="dk"+i;
//            if (!mmkv.contains(s)){
//                mmkv.encode(s,"unknow");
//            }
//        }


        rc=(RecyclerView) view.findViewById(R.id.rc_port);
        toolbar=view.findViewById(R.id.toolbar);
        portModel=new ViewModelProvider(getActivity()).get(PortModel.class);
        bt_search=view.findViewById(R.id.bt_search);


        bt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Search.class));
            }
        });


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        adapter=new RcPortAdapter(mlist, getActivity());
        GridLayoutManager layoutManager=new GridLayoutManager(getActivity(),1);
        rc.setLayoutManager(layoutManager);
        rc.setAdapter(adapter);
        portModel.portsData().observe(getActivity(), new Observer<List<Port>>() {
            @Override
            public void onChanged(List<Port> list) {
                mlist=list;
                adapter.notifyDataSetChanged();
            }
        });
    }


}
