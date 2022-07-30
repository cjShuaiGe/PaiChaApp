package com.example.paichaapp.model;

public class User {
    private String personName;
    private String userName;
    private String password;

    public User(String personName, String userName, String password) {
        this.personName = personName;
        this.userName = userName;
        this.password = password;
    }

    public User() {}

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
