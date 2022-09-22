package com.example.team_project01.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.team_project01.MainActivity;
import com.example.team_project01.R;
import com.example.team_project01.common.CommonVal;
import com.example.team_project01.conn.CommonAskTask;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText login_email, login_pw;
    Button btn_login;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_email = findViewById(R.id.login_email);
        login_pw = findViewById(R.id.login_pw);
        btn_login = findViewById(R.id.btn_login);

     btn_login.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v.getId() ==R.id.btn_login){
            login(login_email.getText().toString(), login_pw.getText().toString(), "N");
        }
    }

    public void login(String email, String pw, String social_yn) {
        CommonAskTask task = new CommonAskTask(LoginActivity.this, "login");
        task.addParams("email",email);  //여기가 고정되면 안돼 edt_getText()+"" -> email 파라메터로 바꿔주기
        task.addParams("pw",pw);
        task.addParams("social", social_yn);
        task.excuteAsk(new CommonAskTask.AsynckTaskCallBack() {
            @Override
            public void onResult(String data, boolean isResult) {

                Log.d("로그인", "onResult: " + data);
                CommonVal.loginInfo = new Gson().fromJson(data, MemberVO.class); //이걸 안해주면 디비랑 다 오류 안나고 회원가입이 잘 되지만...
                //메인액티비티로 안간다 loginInfo 값이 바뀌도록 처리하는거
                //  Toast.makeText(Login_Activity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                if(social_yn.equals("N") && CommonVal.loginInfo == null){
                    Log.d("로그인", "onResult: 아이디비번틀림");
                }else if (social_yn.equals("Y") && CommonVal.loginInfo == null){
                    //이때는 회원가입으로 보내줘야함
                    intent = new Intent(LoginActivity.this, JoinActivity.class);
                    intent.putExtra("email", email);
                    startActivity(intent);
                }else if (CommonVal.loginInfo !=null){
                    //이때는 메인으로 보내줘야함!

                    intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);

                }else {
                    Log.d("로그", "onResult: 세개의 이프문모두 실패!!");


                }


            }
        });

    }

}