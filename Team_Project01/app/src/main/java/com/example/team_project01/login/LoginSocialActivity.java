package com.example.team_project01.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.team_project01.R;

public class LoginSocialActivity extends AppCompatActivity implements View.OnClickListener {

     Button btn_login;
     ImageView imgv_join_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_social);

        btn_login =findViewById(R.id.btn_login);
        imgv_join_email = findViewById(R.id.imgv_join_email);

        btn_login.setOnClickListener(this);
        imgv_join_email.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_login){
            Intent l_intent = new Intent(LoginSocialActivity.this, LoginActivity.class);
            startActivity(l_intent);
        }else if(v.getId() == R.id.imgv_join_email) {
            Intent e_intent = new Intent(LoginSocialActivity.this, JoinActivity.class);
            startActivity(e_intent);
        }
    }


}