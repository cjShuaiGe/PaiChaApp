package com.example.paichaapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.paichaapp.model.Port;

import java.util.ArrayList;
import java.util.List;

public class PortModel extends ViewModel {
    private final MutableLiveData<List<Port>> portsData=new MutableLiveData<>();

    public PortModel() {
        portsData.postValue(new ArrayList<>());
    }

   public void refresh(List<Port> list){
       portsData.setValue(list);
   }
    public MutableLiveData<List<Port>> portsData(){
        return portsData;
    }
}
