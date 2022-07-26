package com.example.paichaapp.model;

public class Port {
    private String portName;
    private String status;
    private String electricConsumption;
    private String maxElectric;
    private String maxVoltage;
    private String maxPower;

    public Port(String portName, String status, String electricConsumption, String maxElectric, String maxVoltage, String maxPower) {
        this.portName = portName;
        this.status = status;
        this.electricConsumption = electricConsumption;
        this.maxElectric = maxElectric;
        this.maxVoltage = maxVoltage;
        this.maxPower = maxPower;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Port() {
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public String getElectricConsumption() {
        return electricConsumption;
    }

    public void setElectricConsumption(String electricConsumption) {
        this.electricConsumption = electricConsumption;
    }

    public String getMaxElectric() {
        return maxElectric;
    }

    public void setMaxElectric(String maxElectric) {
        this.maxElectric = maxElectric;
    }

    public String getMaxVoltage() {
        return maxVoltage;
    }

    public void setMaxVoltage(String maxVoltage) {
        this.maxVoltage = maxVoltage;
    }

    public String getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(String maxPower) {
        this.maxPower = maxPower;
    }
}
