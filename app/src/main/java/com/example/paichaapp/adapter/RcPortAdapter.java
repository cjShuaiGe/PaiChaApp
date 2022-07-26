package com.example.paichaapp.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paichaapp.PortActivity;
import com.example.paichaapp.R;
import com.example.paichaapp.model.Port;

import java.util.List;

public class RcPortAdapter extends RecyclerView.Adapter<RcPortAdapter.ViewHolder> {
    private  List<Port> mlist;
    Activity mactivity;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_name;
        TextView tv_consume;
        TextView tv_maxElectric;
        TextView tv_maxVoltage;
        TextView tv_power;
        Button status;
        View mview;
        public ViewHolder (View view){
            super(view);
            tv_name=view.findViewById(R.id.tv_port_name);
            tv_consume=view.findViewById(R.id.tv_electricity);
            tv_maxElectric=view.findViewById(R.id.tv_max_electric_current);
            tv_maxVoltage=view.findViewById(R.id.max_voltage);
            tv_power=view.findViewById(R.id.max_power);
            status=view.findViewById(R.id.status);
            mview=view;
        }
    }

    public RcPortAdapter(List<Port> list, Activity activity){
        mlist=list;
        mactivity=activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_equipment,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Port port=mlist.get(position);
        holder.tv_name.setText(port.getPortName());
        holder.tv_maxVoltage.setText(port.getMaxVoltage());
        holder.tv_maxElectric.setText(port.getMaxElectric());
        holder.tv_consume.setText(port.getElectricConsumption());
        holder.tv_power.setText(port.getMaxPower());
        holder.status.setText(port.getStatus());
        holder.mview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mactivity, PortActivity.class);
                intent.putExtra("portname",port.getPortName().substring(0,2));
                mactivity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }
}
