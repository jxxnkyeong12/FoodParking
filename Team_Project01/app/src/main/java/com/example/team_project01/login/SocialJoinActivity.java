package com.example.team_project01.login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.team_project01.R;
import com.example.team_project01.conn.CommonAskTask;
import com.google.gson.Gson;

public class SocialJoinActivity extends AppCompatActivity {
    EditText sj_name, sj_phone, sj_email ;
    Button btn_sj_join;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_join);


        sj_name = findViewById(R.id.sj_name);
        sj_phone = findViewById(R.id.sj_phone);
        sj_email = findViewById(R.id.sj_email);
        btn_sj_join = findViewById(R.id.btn_sj_join);


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


        btn_sj_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MemberVO vo = new MemberVO();
                vo.setEmail(intent.getStringExtra("email"));
                vo.setName(intent.getStringExtra("name"));
                vo.setPhone(sj_phone.getText().toString());
                vo.setSocial("Y");


                CommonAskTask askTask = new CommonAskTask(SocialJoinActivity.this, "andJoin");
                askTask.addParams("vo", new Gson().toJson(vo));
                askTask.excuteAsk(new CommonAskTask.AsynckTaskCallBack() {
                    @Override
                    public void onResult(String data, boolean isResult) {

                    }
                });


            }
        });




    }


}