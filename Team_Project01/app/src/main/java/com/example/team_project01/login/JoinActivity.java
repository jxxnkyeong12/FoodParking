package com.example.team_project01.login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.team_project01.R;
import com.example.team_project01.conn.CommonAskTask;
import com.example.team_project01.conn.CommonConn;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

public class JoinActivity extends AppCompatActivity {

        ImageView imgv_join_back;
        Button btn_join_address, btn_join;
        EditText edtv_join_email, edtv_join_pw, edtv_join_pwchk, edtv_join_name
                , edtv_join_nickname, edtv_join_phone, edtv_join_zipcode, edtv_join_address;

        private final int SEARCH_ADDR_CODE = 1001;

        //비밀번호 ** 처리
        //비밀번호 확인 ==  비밀번호


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_join);


            btn_join_address = findViewById(R.id.btn_join_address);

            imgv_join_back = findViewById(R.id.imgv_join_back);
            btn_join_address = findViewById(R.id.btn_join_address);
            btn_join = findViewById(R.id.btn_join);
            edtv_join_email = findViewById(R.id.edtv_join_email);
            edtv_join_pw = findViewById(R.id.edtv_join_pw);
            edtv_join_pwchk = findViewById(R.id.edtv_join_pwchk);
            edtv_join_name = findViewById(R.id.edtv_join_name);
            edtv_join_nickname = findViewById(R.id.detv_join_nickname);
            edtv_join_phone = findViewById(R.id.edtv_join_phone);

            edtv_join_zipcode = findViewById(R.id.edtv_join_zipcode);
            edtv_join_address = findViewById(R.id.edtv_join_address);

            edtv_join_zipcode.setEnabled(false);


            //뒤로가기
            imgv_join_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });

            //우편번호 서비스

            btn_join_address.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(JoinActivity.this, AddressActivity.class);
                    startActivityForResult(intent, SEARCH_ADDR_CODE);
                }
            });


            //회원가입 버튼 클릭
            btn_join.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MemberVO vo = new MemberVO();
                    vo.setEmail(edtv_join_email.getText().toString());
                    vo.setPw(edtv_join_pw.getText().toString());
                    vo.setName(edtv_join_name.getText().toString());
                    vo.setNickname(edtv_join_nickname.getText().toString());
                    vo.setAddr(edtv_join_address.getText().toString());
                    vo.setPhone(edtv_join_phone.getText().toString());
                    // 우편 저장되게 추가 수정 가넝한! jk -2022/09/21
                    vo.setPost(edtv_join_zipcode.getText().toString());
                    vo.setManager("N");

                    CommonConn conn = new CommonConn(JoinActivity.this, "andJoin");
                    conn.addParams("vo", new Gson().toJson(vo));
                    conn.excuteConn(new CommonConn.ConnCallback() {
                        @Override
                        public void onResult(boolean isResult, String data) {

                        }
                    });

                    CommonAskTask askTask = new CommonAskTask(JoinActivity.this, "andJoin");
                    askTask.addParams("vo", new Gson().toJson(vo));
                    askTask.execute(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });

                    Intent intent = new Intent(JoinActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            });

        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode == SEARCH_ADDR_CODE){
                Log.d("TAG", "processDATA: " + data);

                String addr = data.getExtras().getString("addr");
                String post = data.getExtras().getString("post");
                if(data != null){
                    edtv_join_address.setText(addr);
                    edtv_join_zipcode.setText(post);
                }
            }
        }
    }