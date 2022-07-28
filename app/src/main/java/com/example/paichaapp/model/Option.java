package com.example.paichaapp.model;
public class Option {
    private String option;
    private String index_num;

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
}
