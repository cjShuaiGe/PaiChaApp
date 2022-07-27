package com.example.paichaapp.fragment;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
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

import com.example.paichaapp.R;
import com.example.paichaapp.Search;
import com.example.paichaapp.adapter.RcPortAdapter;
import com.example.paichaapp.model.Port;
import com.example.paichaapp.viewmodel.PortModel;
import com.tencent.mmkv.MMKV;

import java.util.ArrayList;
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
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_equipment,container,false);
        init(view);
        initData();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
    }

    private void initData() {
        mlist.clear();
        mlist.add(new Port("端口"+1+":"+mmkv.decodeString("dk1"),"断电","100kw/h","100 A","100 V","100 W"));
        mlist.add(new Port("端口"+2+":"+mmkv.decodeString("dk2"),"通电","100kw/h","100 A","100 V","100 W"));
        mlist.add(new Port("端口"+3+":"+mmkv.decodeString("dk3"),"待机","100kw/h","100 A","100 V","100 W"));
        portModel.refreshPortsData(mlist);
    }


    private void init(View view) {
        mmkv=MMKV.mmkvWithID("id");
        for (int i=1;i<=5;i++){
            String s="dk"+i;
            if (!mmkv.contains(s)){
                mmkv.encode(s,"unknow");
            }
        }


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
