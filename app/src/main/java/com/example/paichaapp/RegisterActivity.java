package com.example.paichaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.paichaapp.LiveData.MyLiveData;
import com.example.paichaapp.Util.Util;
import com.example.paichaapp.model.User;

import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    private boolean isReturn = false;
    private boolean isOk = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_register);
        EditText personNameEditText = (EditText) findViewById(R.id.register_person_name);
        EditText userNameEditText = (EditText) findViewById(R.id.register_user_name);
        EditText passwordEditText = (EditText) findViewById(R.id.register_password);
        EditText passwordAgainEditText = (EditText) findViewById(R.id.register_password_again);
        ImageView backImageView = (ImageView) findViewById(R.id.register_back);
        Button registerButton = (Button) findViewById(R.id.register);
        onResult();
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("".equals(personNameEditText.getText().toString()) || "".equals(userNameEditText.getText().toString()) || "".equals(passwordEditText.getText().toString()) || "".equals(passwordAgainEditText.getText().toString())) {
                    Toast.makeText(RegisterActivity.this,"输入项不能为空！",Toast.LENGTH_SHORT).show();
                } else {
                    if (!passwordEditText.getText().toString().equals(passwordAgainEditText.getText().toString())) {
                        Toast.makeText(RegisterActivity.this,"两次输入密码不相同！请重试！",Toast.LENGTH_SHORT).show();
                    } else {
                        User user = new User(personNameEditText.getText().toString(),userNameEditText.getText().toString(),passwordEditText.getText().toString());
                        Util.sendMessage("{\"option\":\"3\",\"name\":" +"\""+ user.getPersonName() +"\""+ ",\"phone\":" +"\""+ user.getUserName() + "\""+",\"password\":" + "\""+user.getPassword() + "\""+"}");
                    }
                }
            }
        });

        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                intent.putExtra("register","no");
                startActivity(intent);
            }
        });

    }

    protected void onResult() {
        MyLiveData.getMessageData().observe(this, new Observer<String>() {

            @Override
            public void onChanged(String s) {
                String result;
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    if ("1".equals(jsonObject.getString("option"))) {
                    } else {
                        result = jsonObject.getString("result");
                        if (result == "success") {
                            isOk = true;
                        } else {
                            isOk = false;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                onResponse();
            }

        });
//        if (isOk == false) {
//            Toast.makeText(RegisterActivity.this,"注册失败！手机号码已被注册！",Toast.LENGTH_SHORT).show();
//        } else {
//            isOk = false;
//            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
//            intent.putExtra("register","ok");
//            startActivity(intent);
//        }
    }

    private void onResponse() {
        if (isOk == false) {
            Toast.makeText(RegisterActivity.this,"注册失败！手机号码已被注册！",Toast.LENGTH_SHORT).show();
        } else {
            isOk = false;
            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
            intent.putExtra("register","ok");
            startActivity(intent);
        }
    }


}