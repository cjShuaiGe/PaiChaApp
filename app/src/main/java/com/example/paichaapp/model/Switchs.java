package com.example.paichaapp.model;

public class Switchs {
    private String portControl;
    private String switchControl;

    public Switchs(String portName, String switchControl) {
        this.portControl = portName;
        this.switchControl = switchControl;
    }

    public Switchs() {
    }

    public String getPortName() {
        return portControl;
    }

    public void setPortName(String portName) {
        this.portControl = portName;
    }

    public String getSwitchControl() {
        return switchControl;
    }

    public void setSwitchControl(String switchControl) {
        this.switchControl = switchControl;
    }
}
