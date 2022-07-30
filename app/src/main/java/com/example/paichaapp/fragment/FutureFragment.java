package com.example.paichaapp.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.paichaapp.R;
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

import java.util.ArrayList;
import java.util.List;

public class FutureFragment extends Fragment {
    LineChart lc;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_future,container,false);
        lc=view.findViewById(R.id.lc_future);
        setLegend();
        setXAxis();
        setYAxis();
        setData();
        return view;
    }
    private void setData() {
        List<Entry> entries =new ArrayList<>();
        float[] f={(float) 0.0768, (float)0.0948,(float) 0.3353,(float) 0.3914,(float) 0.8789,(float) 0.8006};
        for (int i = 0; i<f.length; i++){
            entries.add(new Entry(i,f[i]));
        }
        LineDataSet lineDataSet=new LineDataSet(entries,"first");
        lineDataSet.setCircleRadius(4);//设置圆点半径大小
        lineDataSet.setLineWidth(2);//设置折线的宽度
        lineDataSet.setFillColor(Color.parseColor("#4CDB96"));//设置线条下方的填充
        lineDataSet.setDrawFilled(true);//是否绘制折线下方的填充
//        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER); // 设置折线类型，这里设置为贝塞尔曲线
        lineDataSet.setCircleColors(Color.parseColor("#4CDB96"));
        lineDataSet.setColor(Color.parseColor("#4CDB96"));
        LineData lineData=new LineData(lineDataSet);
        lc.setData(lineData);
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
                return s.substring(0,s.indexOf("."));
            }
        });
    }

    private void setYAxis() {
        lc.getAxisRight().setEnabled(false);
        YAxis yAxis=lc.getAxisLeft();
        yAxis.setLabelCount(9,true);
//        yAxis.setAxisMaximum(400);
//        yAxis.setAxisMinimum(0);
        yAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                if(value == 0){
                    return "";
                }
                String tep = value + "";
                return tep+"kw/h";
            }
        });

    }

    private void setLegend() {
        Legend legend = lc.getLegend();
        legend.setEnabled(false);
        Description description=lc.getDescription();
        description.setEnabled(false);
    }
}
