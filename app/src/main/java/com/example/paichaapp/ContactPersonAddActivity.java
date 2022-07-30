package com.example.paichaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ContactPersonAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_contact_person_add);
        ImageView backImageView = (ImageView) findViewById(R.id.contact_person_add_back);
        EditText personNameEditText = (EditText) findViewById(R.id.contact_person_add_person_name);
        EditText userNameEditText = (EditText) findViewById(R.id.contact_person_add_user_name);
        Button addButton = (Button) findViewById(R.id.contact_person_add_button);

        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactPersonAddActivity.this,ContactPersonActivity.class);
                intent.putExtra("add","back");
                startActivity(intent);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("".equals(personNameEditText.getText().toString()) || "".equals(userNameEditText.getText().toString())) {
                    Toast.makeText(ContactPersonAddActivity.this,"输入项不能为空！",Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(ContactPersonAddActivity.this,ContactPersonActivity.class);
                    intent.putExtra("add","ok");
                    intent.putExtra("personName",personNameEditText.getText().toString());
                    intent.putExtra("userName",userNameEditText.getText().toString());
                    startActivity(intent);
                }
            }
        });

    }
}