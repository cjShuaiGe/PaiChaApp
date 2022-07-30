package com.example.paichaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_user_information);
        ImageView backImageView = (ImageView) findViewById(R.id.user_information_back);
        TextView personNameTextView = (TextView) findViewById(R.id.user_information_person_name);
        TextView userNameTextView = (TextView) findViewById(R.id.user_information_user_name);
        Intent intent = getIntent();
        personNameTextView.setText(intent.getStringExtra("name"));
        userNameTextView.setText(intent.getStringExtra("userName"));

        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(UserInformationActivity.this,MainActivity.class);
//                startActivity(intent);
                finish();
            }
        });

    }

    //刷新每次传入的Intent
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

}