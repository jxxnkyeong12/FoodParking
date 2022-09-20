package com.example.team_project01.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.team_project01.R;

public class LoginSocialActivity extends AppCompatActivity implements View.OnClickListener {

     Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_social);

        btn_login =findViewById(R.id.btn_login);

        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_login){
            Intent intent = new Intent(LoginSocialActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }
}