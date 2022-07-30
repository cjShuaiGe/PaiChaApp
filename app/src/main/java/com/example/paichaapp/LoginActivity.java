package com.example.paichaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.paichaapp.LiveData.MyLiveData;
import com.example.paichaapp.Util.Util;
import com.example.paichaapp.model.Response;
import com.example.paichaapp.model.User;
import com.google.gson.Gson;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private boolean isOk = false;
    private String name;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_login);
        EditText userNameEditText = (EditText) findViewById(R.id.login_user_name);
        EditText passwordEditText = (EditText) findViewById(R.id.login_password);
        Button loginButton = (Button) findViewById(R.id.login_on);
        TextView registerTextView = (TextView) findViewById(R.id.register);
       onResult();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName = userNameEditText.getText().toString();
                User user = new User("",userNameEditText.getText().toString(),passwordEditText.getText().toString());
                Util.sendMessage("{\"option\":\"2\",\"phone\":" +"\""+ user.getUserName() + "\""+",\"password\":" +"\""+ user.getPassword() + "\""+"}");
            }
        });

        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    //刷新每次传入的Intent
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        if ("ok".equals(intent.getStringExtra("register"))) {
            Toast.makeText(LoginActivity.this,"注册成功！",Toast.LENGTH_SHORT).show();
        } else {
            if ("off".equals(intent.getStringExtra("mine"))) {
                Toast.makeText(LoginActivity.this,"账号已退出！",Toast.LENGTH_SHORT).show();
            } else {
            }
        }
    }

    protected void onResult() {
        MyLiveData.getMessageData().observe(this, new Observer<String>() {

            @Override
            public void onChanged(String s) {
                String result;
//                try {
//                    JSONObject jsonObject = new JSONObject(s);
//                    if ("1".equals(jsonObject.getString("option"))) {
//                    } else {
//                        result = jsonObject.getString("result");
//                        if (result == "success") {
//                            isOk = true;
//                            name = jsonObject.getString("name");
//                        } else {
//                            isOk = false;
//                        }
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                onResponse();

                Response response=new Gson().fromJson(s,Response.class);
                if (response.getOption()!=null){
                if (response.getOption().equals("1")){}else{
                   result= response.getResult();
                   if (result.equals("success")){
                       isOk=true;
                       name=response.getName();

                   }else{isOk=false;}
                }
                onResponse();
            }
            }

        });

    }

    private void onResponse() {
        if (!isOk) {
            Toast.makeText(LoginActivity.this,"登录失败！账号不存在或密码错误！",Toast.LENGTH_SHORT).show();
        } else {
            isOk = false;
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            intent.putExtra("login","ok");
            intent.putExtra("name",name);
            intent.putExtra("userName",userName);
            startActivity(intent);
        }
    }

}