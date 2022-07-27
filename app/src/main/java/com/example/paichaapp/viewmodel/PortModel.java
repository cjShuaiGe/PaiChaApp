package com.example.paichaapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.paichaapp.model.Port;

import java.util.ArrayList;
import java.util.List;

public class PortModel extends ViewModel {
    private String deviceId;
    private final MutableLiveData<List<Port>> portsData = new MutableLiveData<>();
    private final MutableLiveData<String> portName = new MutableLiveData<>();
    private final MutableLiveData<String> deviceName = new MutableLiveData<>();

    public PortModel() {
    }

    public void refreshPortsData(List<Port> list) {
        portsData.setValue(list);
    }

    public void refreshPortName(String s){
        portName.setValue(s);
    }

    public void refreshDeviceName(String s){
        deviceName.setValue(s);
        deviceId=s;
    }

    public MutableLiveData<String> portName(){
        return portName;
    }

    public MutableLiveData<String> getDeviceName(){
        return deviceName;
    }

    public MutableLiveData<List<Port>> portsData() {
        return portsData;
    }

    public String getDeviceId() {
        return deviceId;
    }
}
