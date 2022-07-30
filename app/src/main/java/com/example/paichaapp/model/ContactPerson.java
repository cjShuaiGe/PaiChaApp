package com.example.paichaapp.model;

public class ContactPerson {
    private String personName;
    private String userName;
    private String switchSet;

    public String getSwitchSet() {
        return switchSet;
    }

    public void setSwitchSet(String switchSet) {
        this.switchSet = switchSet;
    }

    public ContactPerson(String personName, String userName, String switchSet) {
        this.personName = personName;
        this.userName = userName;
        this.switchSet = switchSet;
    }

    public ContactPerson() {}

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
}
