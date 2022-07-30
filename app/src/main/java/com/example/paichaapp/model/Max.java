package com.example.paichaapp.model;

public class Max {
    private String current;
    private String voltage;
    private String power;

    public Max(String current, String voltage, String power) {
        this.current = current;
        this.voltage = voltage;
        this.power = power;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }
}
