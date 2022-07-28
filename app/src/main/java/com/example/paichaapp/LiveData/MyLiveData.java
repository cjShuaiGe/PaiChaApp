package com.example.paichaapp.LiveData;

import androidx.lifecycle.MutableLiveData;

public class MyLiveData {
    private static final MutableLiveData<String> messageData =new MutableLiveData<>();

    public  static MutableLiveData<String> getMessageData() {
        return messageData;
    }

    public MyLiveData() {
    }
}
