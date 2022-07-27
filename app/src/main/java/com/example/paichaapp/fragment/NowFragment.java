package com.example.paichaapp.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.paichaapp.R;
import com.example.paichaapp.SetDeviceActivity;
import com.example.paichaapp.tool.ScreenSizeUtils;
import com.example.paichaapp.viewmodel.PortModel;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.tencent.mmkv.MMKV;

import java.util.ArrayList;
import java.util.List;

public class NowFragment extends Fragment {
    PortModel portModel;
    TextView tv_device;
    private LineChart lc;
    Dialog dialog;
    Button set_device;
    Button bt_change_chat;
    Button bt_a;
    Button bt_v;
    Button bt_w;
    TextView tv_type_chat;
    MMKV mmkv;
    String s;
    @SuppressLint("LongLogTag")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragement_now,container,false);
        tv_device=view.findViewById(R.id.tv_device_name);
        portModel=new ViewModelProvider(getActivity()).get(PortModel.class);
        bt_change_chat=view.findViewById(R.id.change_chat);
        set_device=view.findViewById(R.id.set_device);
        tv_type_chat=view.findViewById(R.id.tv_type_chat);
        mmkv=MMKV.mmkvWithID("id");
        Intent intent=getActivity().getIntent();
         s="dk"+intent.getStringExtra("portname").charAt(2);
        portModel.refreshDeviceName(mmkv.decodeString(s));
        lc=view.findViewById(R.id.lc);
        lc.setExtraOffsets(10,30,10,10);
        setChooseChat();
        setModel();
        setXAxis();
        setYAxis();
        setLegend();
        setButton();
        setData();
        return view;
    }

    private void setButton() {
        bt_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_type_chat.setText("电流");
                dialog.dismiss();
            }
        });

        bt_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_type_chat.setText("电压");
                dialog.dismiss();
            }
        });

        bt_w.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_type_chat.setText("功率");
                dialog.dismiss();
            }
        });

        set_device.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), SetDeviceActivity.class);
                intent.putExtra("deviceName",s);
                getActivity().startActivity(intent);
            }
        });
    }

    private void setChooseChat() {
        setDialog();
        bt_change_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
    }

    private void setDialog() {
        dialog=new Dialog(getActivity(),R.style.NormalDialogStyle);
        View view = View.inflate(getActivity(), R.layout.dialog_bottom, null);
        bt_a=view.findViewById(R.id.bt_A);
        bt_v=view.findViewById(R.id.bt_V);
        bt_w=view.findViewById(R.id.bt_W);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
       lp.width = (int) (ScreenSizeUtils.getInstance(getActivity()).getScreenWidth());
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        dialogWindow.setAttributes(lp);

    }

    private void setData() {
        List<Entry> entries =new ArrayList<>();
      for(int i=1;i<6;i++){
          int f=5*i*i+30*i-30;
           entries.add(new Entry(i,f));
      }
        LineDataSet lineDataSet=new LineDataSet(entries,"first");
        lineDataSet.setCircleRadius(4);//设置圆点半径大小
        lineDataSet.setLineWidth(2);//设置折线的宽度
//        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER); // 设置折线类型，这里设置为贝塞尔曲线
        lineDataSet.setCircleColors(Color.parseColor("#4CDB96"));
        lineDataSet.setColor(Color.parseColor("#4CDB96"));
        LineData lineData=new LineData(lineDataSet);
        lc.setData(lineData);
    }

    private void setModel() {
         portModel.getDeviceName().observe(getActivity(), new Observer<String>() {
             @SuppressLint("SetTextI18n")
             @Override
             public void onChanged(String s) {
                 tv_device.setText("设备:"+s);
             }
         });
    }

    private void setXAxis() {
        XAxis xAxis=lc.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);//不绘制网格线
        xAxis.setGranularity(1);//间隔1
//        xAxis.setAxisMaximum(1);//设置最小值
//        xAxis.setAxisMinimum(5);//设置最大值
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                String s=value+"";
                return "06:00:"+s.substring(0,s.indexOf("."));
            }
        });
    }

    private void setYAxis() {
        lc.getAxisRight().setEnabled(false);
        YAxis yAxis=lc.getAxisLeft();
        yAxis.setLabelCount(9,true);
        yAxis.setAxisMaximum(400);
        yAxis.setAxisMinimum(0);
        yAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                if(value == 0){
                    return "";
                }
                String tep = value + "A";
                return tep.substring(0,tep.indexOf("."));
            }
        });

    }

    private void setLegend() {
        Legend legend = lc.getLegend();
       legend.setEnabled(false);
        Description description=lc.getDescription();
        description.setEnabled(false);
    }

    @Override
    public void onStart() {
        super.onStart();
        portModel.refreshDeviceName(mmkv.decodeString(s));
    }
}
