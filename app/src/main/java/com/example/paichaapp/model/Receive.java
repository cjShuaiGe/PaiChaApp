package com.example.paichaapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.Map;
import java.util.logging.LoggingMXBean;

public class Receive {
    Float current;
    Float power;
    String label;
    Float voltage;
    Map<String,String> map;
    String time;
    @SerializedName("1")
    float s1;
    @SerializedName("2")
    float s2;
    @SerializedName("3")
    float s3;
    @SerializedName("4")
    float s4;
    @SerializedName("5")
    float s5;
    @SerializedName("6")
    float s6;
    @SerializedName("7")
    float s7;
    @SerializedName("8")
    float s8;

    Max index_num1;
    Max index_num2;
    Max index_num3;
    String name1;
    String name2;
    String name3;
    String usepower;

    public String getUsepower() {
        return usepower;
    }

    public void setUsepower(String usepower) {
        this.usepower = usepower;
    }

    public Max getIndex_num1() {
        return index_num1;
    }

    public void setIndex_num1(Max index_num1) {
        this.index_num1 = index_num1;
    }

    public Max getIndex_num2() {
        return index_num2;
    }

    public void setIndex_num2(Max index_num2) {
        this.index_num2 = index_num2;
    }

    public Max getIndex_num3() {
        return index_num3;
    }

    public void setIndex_num3(Max index_num3) {
        this.index_num3 = index_num3;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getName3() {
        return name3;
    }

    public void setName3(String name3) {
        this.name3 = name3;
    }

    public float getS1() {
        return s1;
    }

    public float get(int i){
        switch (i){
            case 1:return s1;
            case 2:return s2;
            case 3:return s3;
            case 4:return s4;
            case 5:return s5;
            case 6:return s6;
            case 7:return s7;
            case 8:return s8;
            default:
        }
        return 0;
    }

    public void setS1(float s1) {
        this.s1 = s1;
    }

    public float getS2() {
        return s2;
    }

    public void setS2(float s2) {
        this.s2 = s2;
    }

    public float getS3() {
        return s3;
    }

    public void setS3(float s3) {
        this.s3 = s3;
    }

    public float getS4() {
        return s4;
    }

    public void setS4(float s4) {
        this.s4 = s4;
    }

    public float getS5() {
        return s5;
    }

    public void setS5(float s5) {
        this.s5 = s5;
    }

    public float getS6() {
        return s6;
    }

    public void setS6(float s6) {
        this.s6 = s6;
    }

    public float getS7() {
        return s7;
    }

    public void setS7(float s7) {
        this.s7 = s7;
    }

    public float getS8() {
        return s8;
    }

    public void setS8(float s8) {
        this.s8 = s8;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Float getVoltage() {
        return voltage;
    }

    public void setVoltage(Float voltage) {
        this.voltage = voltage;
    }

    public Float getCurrent() {
        return current;
    }

    public void setCurrent(Float current) {
        this.current = current;
    }

    public Float getPower() {
        return power;
    }

    public void setPower(Float power) {
        this.power = power;
    }


}
