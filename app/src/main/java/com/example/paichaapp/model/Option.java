package com.example.paichaapp.model;
public class Option {
    private String option;
    private String index_num;
    private String date;
   private String key;
   private String value;


    public Option() {
    }

    public void setIndex_num(String index_num) {
        this.index_num = index_num;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Option(String option, String index_num, String date) {
        this.option = option;
        this.index_num = index_num;
        this.date = date;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public Option(String option, String index_num) {
        this.option = option;
        this.index_num = index_num;
    }

    public String getIndex_num() {
        return index_num;
    }

    public String getDate() {
        return date;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
