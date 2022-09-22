package com.example.team_project01.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.example.team_project01.R;

public class SocialJoinActivity extends AppCompatActivity {
    EditText sj_name, sj_phone, sj_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_join);
        sj_name = findViewById(R.id.sj_name);
        sj_phone = findViewById(R.id.sj_phone);
        sj_email = findViewById(R.id.sj_email);

        Intent intent = getIntent();

        if (intent.getStringExtra("name") != null){
            sj_name.setText(intent.getStringExtra("name"));
        }
        if (intent.getStringExtra("phone") != null){
            sj_phone.setText(intent.getStringExtra("phone"));
        }
        if (intent.getStringExtra("email") != null){
            sj_email.setText(intent.getStringExtra("email"));
        }


    }
}