package com.example.paichaapp.fragment;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
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
    List<Port> mlist=new ArrayList<>();
    RcPortAdapter adapter;
    PortModel portModel;

    Toolbar toolbar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_equipment,container,false);
        init(view);
        mlist.add(new Port("端口"+1+":打印机","断电","100kw/h","100 A","100 V","100 W"));
        mlist.add(new Port("端口"+2+":充电宝","通电","100kw/h","100 A","100 V","100 W"));
        mlist.add(new Port("端口"+3+":电脑","待机","100kw/h","100 A","100 V","100 W"));
        portModel.refreshPortsData(mlist);

        return view;
    }

    private void init(View view) {
        rc=(RecyclerView) view.findViewById(R.id.rc_port);
        toolbar=view.findViewById(R.id.toolbar);
        portModel=new ViewModelProvider(getActivity()).get(PortModel.class);
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
