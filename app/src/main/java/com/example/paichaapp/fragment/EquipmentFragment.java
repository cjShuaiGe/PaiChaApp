package com.example.paichaapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paichaapp.R;
import com.example.paichaapp.adapter.RcPortAdapter;
import com.example.paichaapp.model.Port;
import com.example.paichaapp.viewmodel.PortModel;

import java.util.ArrayList;
import java.util.List;

public class EquipmentFragment extends Fragment {
    RecyclerView rc;
    List<Port> list=new ArrayList<>();
    RcPortAdapter adapter;
    PortModel portModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_equipment,container,false);
        rc=(RecyclerView) view.findViewById(R.id.rc_port);
        list.add(new Port("端口"+2+":","断电","100kw/h","100 A","100 V","100 W"));
        list.add(new Port("端口"+1+":","通电","100kw/h","100 A","100 V","100 W"));
        list.add(new Port("端口"+2+":","待机","100kw/h","100 A","100 V","100 W"));
        adapter=new RcPortAdapter(list, getActivity());
        GridLayoutManager layoutManager=new GridLayoutManager(getActivity(),1);
        rc.setLayoutManager(layoutManager);
        rc.setAdapter(adapter);
        return view;
    }
}
