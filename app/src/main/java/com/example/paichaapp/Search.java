package com.example.paichaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.paichaapp.adapter.RcPortAdapter;
import com.example.paichaapp.model.Port;
import com.example.paichaapp.viewmodel.PortModel;
import com.tencent.mmkv.MMKV;

import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView rc;
    EditText et;
    ImageView ivClear;
    List<Port> list=new ArrayList<>();
    List<Port> serList=new ArrayList<>();
    RcPortAdapter adapter;
    MMKV mmkv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        toolbar=findViewById(R.id.toolbar_search);
        et=findViewById(R.id.et_goods);
        ivClear=findViewById(R.id.iv_clear);
        rc=findViewById(R.id.rc_search);
        mmkv=MMKV.mmkvWithID("id");
        initList();
        initRecyclerView();

        setSearchEdit();

    }

    private void initList() {
        list.clear();
       for (int i=1;i<=3;i++){
           list.add(new Port(mmkv.decodeString("dk"+i),mmkv.decodeString("status"+i),mmkv.decodeString("usePower"+i),mmkv.decodeString("current"+i),
                   mmkv.decodeString("voltage"+i),mmkv.decodeString("power"+i)));
       }
    }

    private void initRecyclerView() {
        adapter=new RcPortAdapter(serList,Search.this);
        GridLayoutManager layoutManager=new GridLayoutManager(Search.this,1);
        rc.setLayoutManager(layoutManager);
        rc.setAdapter(adapter);
    }

    private void setSearchEdit() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().length()<1){
                    ivClear.setVisibility(View.INVISIBLE);
                }else {
                    ivClear.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        et.setOnEditorActionListener((textView, i, keyEvent) -> {
            if(i== EditorInfo.IME_ACTION_SEARCH){
                String word=et.getText().toString().trim();
                if(word.isEmpty()){
                    Toast.makeText(Search.this,"请输入设备名",Toast.LENGTH_SHORT).show();
                }else {
                    serList.clear();
                     for (Port port:list){
                         if (word.equals(port.getPortName().substring(0,3))){
                             serList.add(port);
                         } else if (word.equals(port.getPortName().substring(4))){
                             serList.add(port);
                         }
                     }
                }

                adapter.notifyDataSetChanged();
                return true;
            }
            return false;
        });

        ivClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et.setText("");
            }
        });
    }
}